package com.share1024.java.java8;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/11
 */
public class UserServiceImpl implements UserService {

    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.say();
        UserService.sayStatic();

    }
}
