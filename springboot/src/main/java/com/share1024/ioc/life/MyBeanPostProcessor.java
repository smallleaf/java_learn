package com.share1024.ioc.life;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("============6.这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }

    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out
                .println("============BeanPostProcessor 对象初始化之后，BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！===========");
        return arg0;
    }

    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out
                .println("================BeanPostProcessor 对象初始化之前，BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
        return arg0;
    }
}
