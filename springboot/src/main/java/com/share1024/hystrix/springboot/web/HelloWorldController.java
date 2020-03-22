package com.share1024.hystrix.springboot.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloWorldController {

    @HystrixCommand(fallbackMethod = "onError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maximumSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("name不能为空");
        }
        return "Hello, " + name;
    }

    @HystrixCommand
    @RequestMapping("/sayHi")
    public String sayHi(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("name不能为空");
        }
        return "Good morning, " + name;
    }

    /**
     * 如果fallback方法的参数和原方法参数个数不一致，则会出现FallbackDefinitionException: fallback method wasn't found
     */
    public String onError(String name) {
        return "Error!!!" + name;
    }
}
