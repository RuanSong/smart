package com.framework.smart.helper;

import org.framework.smart.util.ClassUtil;

/**
 * 加载相应的helper类
 *
 * @author rosan
 * @date: 2017/10/14 下午9:57
 * @version:1.0
 */
public final class HelperLoader {
    public static void init() {
        Class<?> classList[] = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
