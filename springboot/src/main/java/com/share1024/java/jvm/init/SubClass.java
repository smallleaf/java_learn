package com.share1024.java.jvm.init;

/**
 * \* @Author: yesheng
 * \* Date: 2021/4/1 10:03
 * \* Description:
 * \
 */
public class SubClass extends ParentClass{
    private static final int a= 100;

    private static int b = 200;
    int c= 300;
    private Object object = new Object();
    static {
        System.out.println(String.format("sub a:%s,b%s",a,b));
    }

    {
        System.out.println(String.format("sub a:%s,b%s,c%s,object {}",a,b,c,object == null ? "null":"no"));
    }

    public SubClass(){
        System.out.println(String.format("sub init ,a:%s,b%s,c%s,object {}",a,b,c,object == null ? "null":"no"));
    }

    public static void main(String[] args) {
        new JvmTest();
    }

}