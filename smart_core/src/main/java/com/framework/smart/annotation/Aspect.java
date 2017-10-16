package com.framework.smart.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 *
 * @author rosan
 * @date: 2017/10/16 下午8:40
 * @version:1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 注解
     *
     * @return
     */
    Class<? extends Annotation> value();
}
