package com.share1024.aop.demo02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/14
 */
public class ProxyTest{

//    private ApplicationContext applicationContext ;
//    @Override
//    public void afterPropertiesSet() throws Exception {
////        Person man = (Person) applicationContext.getBean("manProxy");
////        man.say();
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProxyConfig.class);
        Person man = (Person) applicationContext.getBean("manProxy");
        man.say();

    }
}
