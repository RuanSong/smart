package com.framework.smart.proxy;

/**
 * 代理接口
 *
 * @author rosan
 * @date: 2017/10/16 下午8:43
 * @version:1.0
 */
public interface Proxy {
    /**
     * 执行链式代理
     *
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
