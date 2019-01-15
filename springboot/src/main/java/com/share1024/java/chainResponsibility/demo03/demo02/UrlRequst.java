package com.share1024.java.chainResponsibility.demo03.demo02;


/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class UrlRequst implements IUrl {

    public void say(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("i am url");
    }
}
