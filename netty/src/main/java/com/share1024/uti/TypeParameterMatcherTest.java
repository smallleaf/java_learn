package com.share1024.uti;

import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.TypeParameterMatcher;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/15 11:14
 * \* Description:
 * \
 */
public class TypeParameterMatcherTest<T> {

    T obj;
    public TypeParameterMatcher matcher;

    public TypeParameterMatcherTest(){
        matcher = TypeParameterMatcher.find(this, TypeParameterMatcherTest.class, "T");
        System.out.println("===");
    }


    public static void main(String[] args) {
        TypeParameterMatcherTest<String> test = new TypeParameterMatcherTest<>();
        if(test.matcher.match("String")){

        }
    }
}