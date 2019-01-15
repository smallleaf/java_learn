package com.share1024.java.chainResponsibility;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class CrossFilterChain extends FilterChain{

    public CrossFilterChain(){
    }


    @Override
    void before() {
        System.out.println(" i am CrossFilterChain");
    }
}
