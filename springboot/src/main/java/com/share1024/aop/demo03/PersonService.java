package com.share1024.aop.demo03;

import org.springframework.stereotype.Component;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/15
 */
@Component
public class PersonService implements IPerson{

    public void say(){
        System.out.println("=======jajaj===i am say====");
    }
}
