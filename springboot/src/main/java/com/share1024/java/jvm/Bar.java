package com.share1024.java.jvm;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/13
 */
class Bar extends Foo {
    int j = 1;

    Bar() {
        j = 2;
    }

    {
        j = 3;
    }

    @Override
    protected int getValue() {
        return j;
    }
}
