package com.share1024.java.chainResponsibility.demo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class Utf8Chainn implements UrlChain, InvocationHandler {


    private Object target;
    @Override
    public Object chain(Object urlChain) {
        this.target = urlChain;
        return Proxy.newProxyInstance(urlChain.getClass().getClassLoader(),urlChain.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" i am urtf8 ");
        return method.invoke(target,args);
    }
}
