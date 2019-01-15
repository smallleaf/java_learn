package com.share1024.delay;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/12
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    @PostConstruct
    public void send(){
        String message = "Hello World!";
        Order order = new Order();
        order.setOrderName("哈哈哈哈");
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setDelay(20000);
//        Message message1 = new Message(JSON.toJSONBytes(order),messageProperties);
//        amqpTemplate.send(OrderRabbitConfig.ORDER_EXCHANGE_NAME,OrderRabbitConfig.ORDER_ROUTING_KEY,message1);
////        amqpTemplate.convertAndSend(OrderRabbitConfig.ORDER_EXCHANGE_NAME,OrderRabbitConfig.ORDER_ROUTING_KEY,message1,new ExpirationMessagePostProcessor(3000L));


        amqpTemplate.convertAndSend(OrderRabbitConfig.ORDER_DELAY_EXCHANGE,OrderRabbitConfig.ORDER_DELAY_ROUTING_KEY,order,message2 -> {
            message2.getMessageProperties().setExpiration(5000+"");
            return message2;
        });
        System.out.println(" [x] Sent '" + message + "'");
    }
}
