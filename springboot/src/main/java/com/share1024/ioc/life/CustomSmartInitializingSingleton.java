package com.share1024.ioc.life;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/3 20:23
 * \* Description:
 * \
 */
@Component
public class CustomSmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("=====================afterSingletonsInstantiated===============================");
    }
}