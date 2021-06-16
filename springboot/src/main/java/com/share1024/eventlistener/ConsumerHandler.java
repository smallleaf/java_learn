package com.share1024.eventlistener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/3 19:12
 * \* Description:
 * \
 */
@Component
public class ConsumerHandler {

    @EventListener
    public void listener(ConsumerEvent<String> event){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(event.getData());
    }
};