package com.share1024.java.proxy;

public class APp {

    public static void main(String[] args) {
        UserServiceProxy userServiceProxy = new UserServiceProxy(new UserService());
        IUserService userService = (IUserService) userServiceProxy.getProxy();
        userService.test();

    }
}
