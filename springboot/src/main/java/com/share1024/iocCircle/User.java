package com.share1024.iocCircle;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(User.class)
@ConfigurationProperties(prefix = "user")
public class User {

   private int test;


    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
