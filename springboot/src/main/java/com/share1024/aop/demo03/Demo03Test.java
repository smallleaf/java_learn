package com.share1024.aop.demo03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/15
 */
@Component
public class Demo03Test {

    @Autowired
    private IPerson person;

    @PostConstruct
    public void init(){
        person.say();
    }
}
