package org.framework.smart.helper;

import org.framework.smart.annotation.Action;
import org.framework.smart.bean.Handler;
import org.framework.smart.bean.Request;
import org.framework.smart.util.ArrayUtil;
import org.framework.smart.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器帮助类
 *
 * @author rosan
 * @date: 2017/10/14 下午9:38
 * @version:1.0
 */
public class ControllerHelper {
    /**
     * 用户存放请求与处理器的映射关系(Action Map)
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        /**
         * 获取所有的cotroller类
         */
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            /**
             * 遍历所有的Controller类
             */
            for (Class<?> controllerClass : controllerClassSet) {
                /**
                 * 获取Controller类中定义的方法
                 */
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    /**
                     * 遍历Controller定义的方法
                     */
                    for (Method method : methods) {
                        /**
                         * 判断是有的Action注解
                         */
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            /**
                             * 从Action中获取URL映射规则
                             */
                            String mapping = action.value();
                            if (mapping.matches("\\w+:\\w*")) {
                                String[] array = mapping.split(":");
                                /**
                                 * 检验URL映射规则
                                 */
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    /**
                                     * 获取请求规则和请求路径
                                     */
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    /**
                                     * 初始化Action Map
                                     */
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     *
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
