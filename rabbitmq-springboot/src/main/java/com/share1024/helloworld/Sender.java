package com.share1024.helloworld;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/11
 */
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;



//    @Autowired
//    private FanoutExchange fanout;


    @Autowired
    private DirectExchange direct;


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        String message = "Hello World!";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(2000);
        Message message1 = new Message(message.getBytes(),messageProperties);
        //amqpTemplate.send(direct.getName(),"key2",message1);
//        amqpTemplate.convertAndSend(exchange.getName(),"yesheng.key2", message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
