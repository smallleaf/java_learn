package com.share1024.java.chainResponsibility.demo02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class App {

    public static void main(String[] args) {

        UrlRequst urlRequst = new UrlRequst();
        List<UrlChain> urlChainList = new ArrayList<>();
        urlChainList.add(new BeforeChain());
        urlChainList.add(new AfterChain());
        urlChainList.add(new Utf8Chainn());
        ChainHandler chainHandler = new ChainHandler();
        chainHandler.setUrlChainList(urlChainList);
        IUrl url = (IUrl) chainHandler.wrap(urlRequst);
        url.say();
     }
}
