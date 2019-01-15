package com.share1024.java.chainResponsibility.demo03.demo02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public class App {

    public static void main(String[] args) {

        List<Advice> adviceList = new ArrayList<>();
        adviceList.add(new UrlAdvice());
        adviceList.add(new UrlAdvice());
        adviceList.add(new UrlAdvice());
        IUrl iUrl = new UrlRequst();
        for(int i=0;i< adviceList.size();i++){
            iUrl = (IUrl) adviceList.get(i).wrap(iUrl,i);
        }
        iUrl.say();
     }
}
