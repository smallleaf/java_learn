package com.share1024.ioc.life;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/16
 */
@Component
public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

    public MyMergedBeanDefinitionPostProcessor(){
        System.out.println("======8.MergedBeanDefinitionPostProcessor实例化，这个也是BeanPostProcessor");
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        System.out.println("======MyMergedBeanDefinitionPostProcessor   postProcessMergedBeanDefinition");
    }
}
