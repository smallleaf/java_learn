package com.share1024.java.chainResponsibility;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class UrlFilterChain extends FilterChain{


    @Override
    void before() {
        System.out.println(" i am UrlFilterChain");
    }
}
