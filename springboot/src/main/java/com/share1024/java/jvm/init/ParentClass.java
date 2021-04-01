package com.share1024.java.jvm.init;

/**
 * \* @Author: yesheng
 * \* Date: 2021/4/1 10:03
 * \* Description:
 * \
 */
public class ParentClass {
    private static final int a= 100;

    int c= 300;
    private Object object = new Object();
    static {
        System.out.println(String.format("a:%s",a));
    }

    {
        System.out.println(String.format("a:%s,b%s,c%s,object {}",a,b,c,object == null ? "null":"no"));
    }

     static int b = 200;

    public ParentClass(){
        System.out.println(String.format("init ,a:%s,b%s,c%s,object {}",a,b,c,object == null ? "null":"no"));
    }

}