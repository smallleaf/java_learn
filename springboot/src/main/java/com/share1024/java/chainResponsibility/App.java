package com.share1024.java.chainResponsibility;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class App {

    public static void main(String[] args) {

        CrossFilterChain filterChain = new CrossFilterChain();
        UtfFilterChain utfFilterChain = new UtfFilterChain();
        filterChain.setFilterChain(utfFilterChain);
        UrlFilterChain urlFilterChain = new UrlFilterChain();
        utfFilterChain.setFilterChain(urlFilterChain);


        filterChain.say();

    }
}
