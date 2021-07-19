package com.share1024.yml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * \* @Author: yesheng
 * \* Date: 2021/7/19 13:09
 * \* Description:
 * \
 */
@Configuration
@EnableConfigurationProperties(TestConfig.class)
public class JavaConfig implements EnvironmentAware {

    @Autowired
    private TestConfig config;

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("===");
    }

}