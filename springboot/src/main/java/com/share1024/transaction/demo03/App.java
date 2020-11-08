package com.share1024.transaction.demo03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-25
 */
@SpringBootApplication
public class App {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init(){
        userService.save();
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
