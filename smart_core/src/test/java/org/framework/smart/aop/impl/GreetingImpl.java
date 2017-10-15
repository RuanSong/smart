package org.framework.smart.aop.impl;

import org.framework.smart.aop.Greeting;

/**
 * 默认实现，after() before()代码写死掉了
 *
 * @author rosan
 * @date: 2017/10/15 下午9:32
 * @version:1.0
 */
public class GreetingImpl implements Greeting {
    public void sayHello(String name) {
        before();
        System.out.println("hello ," + name);
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");

    }
}
