package com.share1024.ioc.life;


import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

//@Component
public class MyInstantiationAwareBeanPostProcessor implements
        InstantiationAwareBeanPostProcessor {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out
                .println("============7.这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！这个也是PostProcessor========");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,
                                                 String beanName) throws BeansException {
        System.out
                .println("============实例化之前调用,InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法");
        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out
                .println("============实例化之后调用，InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
                                                    PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        System.out
                .println("================InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return pvs;
    }
}