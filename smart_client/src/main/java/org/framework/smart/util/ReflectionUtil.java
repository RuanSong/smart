package org.framework.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author rosan
 * @date: 2017/10/14 下午9:11
 * @version:1.0
 */
public final class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     *
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failed", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (Exception e) {
            logger.error("invoke method failed...", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量值
     *
     * @param object
     * @param field
     * @param value
     */
    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            logger.error("set field failed...", e);
            throw new RuntimeException(e);
        }
    }
}
