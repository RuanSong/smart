package com.framework.smart;

import com.framework.smart.bean.Data;
import com.framework.smart.bean.Handler;
import com.framework.smart.bean.Param;
import com.framework.smart.bean.View;
import com.framework.smart.helper.BeanHelper;
import com.framework.smart.helper.ConfigHelper;
import com.framework.smart.helper.ControllerHelper;
import com.framework.smart.helper.HelperLoader;
import org.framework.smart.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器
 *
 * @author rosan
 * @date: 2017/10/14 下午10:24
 * @version:1.0
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        /**
         * 舒适化相关helper类
         */
        HelperLoader.init();
        /**
         * 获取servletContext对象(用于注册servlet)
         */
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppAsetPath() + "*");
        /**
         * 注册处理静态资源默认的Servlet
         */
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAsetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 请求获取方法与请求路径
         */
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();
        /**
         * 获取Action处理器
         */
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            /**
             * 获取Controller类以及Bean实例
             */
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            /**
             * 创建请求参数对象
             */
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if (StringUtil.isNotEmply(body)) {
                String[] params = StringUtil.splitString(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtil.splitString(param, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            Method actionMethod = handler.getActionMethod();
            /**
             * 调用Action方法
             */
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            /**
             * 处理Action 方法返回值
             */
            if (result instanceof View) {
                /**
                 * 返回视图界面
                 */
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmply(path)) {
                    if (path.startsWith("/")) {
                        response.sendRedirect(request.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                        request.getRequestDispatcher(ConfigHelper.getAppViewPath() + path).forward(request, response);
                    }
                }
            } else if (result instanceof Data) {
                /**
                 * 返回JSON数据
                 */
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
