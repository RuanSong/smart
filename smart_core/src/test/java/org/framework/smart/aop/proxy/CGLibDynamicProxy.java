package org.framework.smart.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author rosan
 * @date: 2017/10/15 下午9:42
 * @version:1.0
 */
public class CGLibDynamicProxy implements MethodInterceptor {
    private static CGLibDynamicProxy instance = new CGLibDynamicProxy();

    public CGLibDynamicProxy() {
    }

    public static CGLibDynamicProxy getInstance() {
        return instance;
    }

    public <T> T getProxy(Class<?> cls) {
        return (T) Enhancer.create(cls, this);
    }

    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");

    }
}
