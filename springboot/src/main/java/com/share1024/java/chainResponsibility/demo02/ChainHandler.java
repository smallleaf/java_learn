package com.share1024.java.chainResponsibility.demo02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class ChainHandler {

    private List<UrlChain> urlChainList = new ArrayList<>();

    public Object wrap(Object object){
        for (UrlChain urlChain : urlChainList) {
            object = urlChain.chain(object);
        }
        return object;
    }


    public List<UrlChain> getUrlChainList() {
        return urlChainList;
    }

    public void setUrlChainList(List<UrlChain> urlChainList) {
        this.urlChainList = urlChainList;
    }
}
