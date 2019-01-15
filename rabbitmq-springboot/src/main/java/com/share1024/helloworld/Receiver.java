package com.share1024.helloworld;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/11
 */
public class Receiver {

    private String name;

    public Receiver(String name) {
        this.name = name;
    }

//    @RabbitListener(queues="autoDeleteQueue1")
//    public void receive(String in) {
//        System.out.println(name+" [x] Received '" + in + "'");
//    }


    @RabbitListener(queues="autoDeleteQueue1")
    public void receive2(String in) {
        System.out.println(name+" [x] Received2 '" + in + "'");
    }


}
