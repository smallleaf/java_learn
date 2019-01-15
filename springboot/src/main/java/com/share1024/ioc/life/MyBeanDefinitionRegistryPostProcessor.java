package com.share1024.ioc.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/16
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    public MyBeanDefinitionRegistryPostProcessor(){
        System.out.println("============MyBeanDefinitionRegistryPostProcessor 构造器");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("============MyBeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry");

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("============MyBeanDefinitionRegistryPostProcessor postProcessBeanFactory");

    }
}
