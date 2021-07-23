package com.share1024.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * \* @Author: yesheng
 * \* Date: 2021/7/19 13:01
 * \* Description:
 * \
 */
@ConfigurationProperties(prefix = "spring.yml")
public class TestConfig {
    private Map<String,TestConfig1> data = new HashMap<>();

    public Map<String, TestConfig1> getData() {
        return data;
    }

    public void setData(Map<String, TestConfig1> data) {
        this.data = data;
    }
}