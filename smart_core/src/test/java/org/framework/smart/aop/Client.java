package org.framework.smart.aop;

import org.framework.smart.aop.impl.GreetingImpl;
import org.framework.smart.aop.proxy.CGLibDynamicProxy;
import org.framework.smart.aop.proxy.GreetingProxy;
import org.framework.smart.aop.proxy.JDKDynamicProxy;
import org.junit.Test;

/**
 * @author rosan
 * @date: 2017/10/15 下午9:46
 * @version:1.0
 */
public class Client {
    @Test
    public void testNormalAopClient(){
        Greeting greeting = new GreetingImpl();
        greeting.sayHello("Rosan");
    }
    @Test
    public void testGreetingProxyClient(){
        GreetingProxy proxy = new GreetingProxy(new GreetingImpl());
        proxy.sayHello("Rosan");
    }
    @Test
    public void testJdkProxyClient(){
        Greeting greeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
        greeting.sayHello("Rosan");
    }
    @Test
    public void testCglibProxyClient(){
        Greeting greeting = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
        greeting.sayHello("Rosan");
    }

}
