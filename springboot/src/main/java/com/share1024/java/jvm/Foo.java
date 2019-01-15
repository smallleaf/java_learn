package com.share1024.java.jvm;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/13
 */
public class Foo {

    int i = 1;

    Foo() {
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
    }

    {
        i = 2;
    }

    protected int getValue() {
        return i;
    }
}



