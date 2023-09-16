package com.share1024.cat.controller;

import com.dianping.cat.Cat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Date 2023年09月03日
 * @Created by yesheng
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
//        int i = 10/0;
        Cat.logEvent("test","123");
        return "ok";
    }
}
