package com.share1024.resilience4J.springboot.web;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

/**
 * \* @Author: yesheng
 * \* Date: 2020/6/15 17:03
 * \* Description:
 * \
 */
@Service
public class TestService {

    @CircuitBreaker(name = "test1",fallbackMethod = "fallBack")
    public String test(int i){
        int k = 10/i;
        return "ok";
    }


    public String fallBack(int i,RuntimeException e){
        return "请求错误" + e.getMessage();
    }
}