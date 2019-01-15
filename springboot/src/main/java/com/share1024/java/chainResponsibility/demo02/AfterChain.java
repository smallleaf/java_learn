package com.share1024.java.chainResponsibility.demo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class AfterChain implements UrlChain, InvocationHandler {
    private Object target;
    @Override
    public Object chain(Object urlChain) {
        this.target = urlChain;
        return Proxy.newProxyInstance(urlChain.getClass().getClassLoader(),urlChain.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target);
        System.out.println(" i am after");
        return result;
    }
}
