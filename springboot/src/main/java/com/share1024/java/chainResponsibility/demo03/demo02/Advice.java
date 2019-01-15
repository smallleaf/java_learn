package com.share1024.java.chainResponsibility.demo03.demo02;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public interface Advice {

    void before();

    void after();


    Object wrap(Object object,int i);
}
