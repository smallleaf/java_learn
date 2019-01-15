package com.share1024.delay;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/12
 */
@Service
public class Reciever {

    @RabbitListener(queues = OrderRabbitConfig.ORDER_QUEUE_NAME)
    public void handler(Order order, Message message, Channel channel){
        System.out.println("===="+order.toString());
    }
}
