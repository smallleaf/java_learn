package com.share1024.aop.demo02;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/14
 */
public class ProxyConfig {

    @Bean
    public Person man(){
        return new Man();
    }

    @Bean
    public CustomBeforeAdvice customBeforeAdvice(){
        return new CustomBeforeAdvice();
    }

//    @Bean
//    public JdkRegexpMethodPointcut queryPointcut(){
//        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
//        pointcut.setPattern("say.*");
//        return pointcut;
//    }
//
//    @Bean
//    public DefaultPointcutAdvisor queryAdvisor(){
//        DefaultPointcutAdvisor queryAdvisor = new DefaultPointcutAdvisor();
//        queryAdvisor.setPointcut(Pointcut.TRUE);
//        queryAdvisor.setAdvice(customBeforeAdvice());
//        return queryAdvisor;
//    }
//
//
//    @Bean
//    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor(){
//        RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
//        regexpMethodPointcutAdvisor.setPattern("say.*");
//        regexpMethodPointcutAdvisor.setAdvice(customBeforeAdvice());
//        return regexpMethodPointcutAdvisor;
//    }
    @Bean
    public ProxyFactoryBean manProxy(Person man){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames("customBeforeAdvice");
        proxyFactoryBean.setInterfaces(Person.class.getInterfaces());
        proxyFactoryBean.setTarget(man);
        return proxyFactoryBean;
    }

}
