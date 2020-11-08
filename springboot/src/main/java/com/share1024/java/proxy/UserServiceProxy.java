package com.share1024.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceProxy implements InvocationHandler {

    private IUserService userService;
    public UserServiceProxy(IUserService userService){
        this.userService = userService;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),userService.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("before");
        method.invoke(userService,args);
        System.out.println("after");

        return null;
    }
}
