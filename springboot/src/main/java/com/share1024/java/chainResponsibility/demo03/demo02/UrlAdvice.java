package com.share1024.java.chainResponsibility.demo03.demo02;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class UrlAdvice implements Advice {
    @Override
    public void before() {
        System.out.println(" i url before");
    }

    @Override
    public void after() {
        System.out.println("i url after");
    }

    @Override
    public Object wrap(Object object,int i) {
        return new AdviceHandler().warp(object,this,i);
    }
}
