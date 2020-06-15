package com.share1024.resilience4J.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* @Author: yesheng
 * \* Date: 2020/6/15 17:02
 * \* Description:
 * \
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test/{id}")
    public  String test(@PathVariable("id") int id){
        return testService.test(id);
    }
}