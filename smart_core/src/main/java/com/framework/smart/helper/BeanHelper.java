package com.framework.smart.helper;

import org.framework.smart.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean初始化帮助类
 *
 * @author rosan
 * @date: 2017/10/14 下午9:18
 * @version:1.0
 */
public final class BeanHelper {
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object object = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, object);
        }
    }

    /**
     * 获取Bean映射
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取bean实例
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class:" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置bean实例
     *
     * @param cls
     * @param object
     */
    public static void setBean(Class<?> cls, Object object) {
        BEAN_MAP.put(cls, object);
    }
}
