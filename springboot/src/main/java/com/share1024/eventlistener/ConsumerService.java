package com.share1024.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/3 19:14
 * \* Description:
 * \
 */
@Service
public class ConsumerService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void test(String data){
        ConsumerEvent<String> event = new ConsumerEvent<>("",data);
        eventPublisher.publishEvent(event);
    }
}