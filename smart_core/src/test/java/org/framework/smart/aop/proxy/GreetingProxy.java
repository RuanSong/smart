package org.framework.smart.aop.proxy;

import org.framework.smart.aop.Greeting;
import org.framework.smart.aop.impl.GreetingImpl;

/**
 * 静态代理实现,如果有若干个调用的时候，会存在很多个xxxProxy的代码
 *
 * @author rosan
 * @date: 2017/10/15 下午9:34
 * @version:1.0
 */
public class GreetingProxy implements Greeting {
    private GreetingImpl greetingImpl;

    public GreetingProxy(GreetingImpl greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    public void sayHello(String name) {
        before();
        greetingImpl.sayHello(name);
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");

    }
}
