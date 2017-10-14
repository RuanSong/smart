package com.framework.smart.helper;

import com.framework.smart.annotation.Resource;
import org.framework.smart.util.ArrayUtil;
import org.framework.smart.util.CollectionUtil;
import org.framework.smart.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入帮助类
 *
 * @author rosan
 * @date: 2017/10/14 下午9:25
 * @version:1.0
 */
public final class IocHelper {
    static {
        /**
         * 获取所有的Bean类与Bean实例之间的映射关系(获取BeanMap)
         */
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            /**
             * 遍历Bean Map
             */
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                /**
                 * 从beanMap中获取Bean类与Bean实例
                 */
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                /**
                 * 获取Bean类定义的所有成员变量(Bean Field)
                 */
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    /**
                     * 遍历Bean Field
                     */
                    for (Field beanField : beanFields) {
                        /**
                         * 判断当前Bean Field是否带有Resource注解
                         */
                        if (beanField.isAnnotationPresent(Resource.class)) {
                            /**
                             * 在BeanMap中获取Bean Field对应的实例
                             */
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanInstance != null) {
                                /**
                                 * 通过反射初始化Bean Field的值
                                 */
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
