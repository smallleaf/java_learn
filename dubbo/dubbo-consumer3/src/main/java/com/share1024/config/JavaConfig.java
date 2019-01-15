package com.share1024.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.share1024.api.IOrderService;

import javax.annotation.PostConstruct;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/15
 */
public class JavaConfig {

    @Reference(loadbalance = "random")
    private IOrderService orderService;

    @PostConstruct
    public void init(){
        System.out.println("====");
    }
}
