package com.share1024.java.chainResponsibility.demo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class UrlChainInvokeHandler implements InvocationHandler {

    private Object target;


    public Object proxy(Object object){
        this.target = object;
        return Proxy.newProxyInstance(UrlChainInvokeHandler.class.getClassLoader(),object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=====");
        return method.invoke(target,args);
    }


}
