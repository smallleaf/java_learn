package com.share1024.aop.demo03;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * \* @Author: yesheng
 * \* Date: 2021/4/22 20:21
 * \* Description:
 * \
 */
@Configuration
public class JavaConfig {

    @Bean
    public Test test(ObjectProvider<List<User>> users ){
        System.out.println("==="+users.getIfAvailable().size());
        return new Test();
    }
}