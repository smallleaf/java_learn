package com.share1024.transaction.demo02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-25
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService userService = (UserService)applicationContext.getBean("proxyFactoryBean");
        userService.save();
    }
}
