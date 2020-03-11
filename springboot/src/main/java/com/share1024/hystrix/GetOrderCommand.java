package com.share1024.hystrix;

import com.netflix.hystrix.*;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/9 20:28
 * \* Description:
 * \
 */
public class GetOrderCommand extends HystrixCommand<String> {


    protected GetOrderCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(5000)
                )
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withMaxQueueSize(10)   //配置队列大小
                                .withCoreSize(2)    // 配置线程池里的线程数
                )
        );
    }

    @Override
    protected String run() throws Exception {
        return "hhehehe";
    }


    public static void main(String[] args) {
        String name =  new GetOrderCommand("test").execute();
        System.out.println(name);
    }
}