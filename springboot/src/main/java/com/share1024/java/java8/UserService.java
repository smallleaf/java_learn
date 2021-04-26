package com.share1024.java.java8;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/11
 */
public interface UserService {

    default void say(){
        System.out.println("hahaha");
    }


    static void sayStatic(){
        System.out.println("iam static");
    }


    void player(PlayerAction playerAction);

    String test2();
}
