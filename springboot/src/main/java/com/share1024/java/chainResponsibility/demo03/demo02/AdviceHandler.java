package com.share1024.java.chainResponsibility.demo03.demo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class AdviceHandler implements InvocationHandler {

    private Object object;

    private Advice advice;

    public int i;

    public Object warp(Object object,Advice advice,int i){
        this.advice = advice;
        this.object = object;
        this.i = i;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(i);
        advice.before();
        System.out.println(object.getClass().getName());
        Object result = method.invoke(object,args);
        System.out.println(i);
        advice.after();
        return result;
    }
}
