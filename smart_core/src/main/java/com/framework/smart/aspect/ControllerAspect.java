package com.framework.smart.aspect;

import com.framework.smart.annotation.Aspect;
import com.framework.smart.annotation.Controller;
import com.framework.smart.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 拦截Controller的所有方法
 *
 * @author rosan
 * @date: 2017/10/16 下午9:45
 * @version:1.0
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.debug("----------- begin ---------");
        logger.debug(String.format("class: %s", cls.getName()));
        logger.debug(String.format("method: %s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.debug(String.format("time : %dms", System.currentTimeMillis() - begin));
        logger.debug("----------- end ---------");
    }
}
