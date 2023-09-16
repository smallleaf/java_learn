package com.share1024.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description
 * @Date 2023年09月03日
 * @Created by yesheng
 */
@SpringBootApplication(scanBasePackages = "com.share1024")
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.run(Application.class, args);
    }
}
