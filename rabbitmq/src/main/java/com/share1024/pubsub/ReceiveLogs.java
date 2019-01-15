package com.share1024.pubsub;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/9/30
 */
public class ReceiveLogs {

    private static final String EXCHANGE_NAME = "logs2";

    private static final String ROUTING_KEY = "route";


    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.168.201");
        factory.setUsername("yesheng");
        factory.setPassword("yesheng");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
        channel.queueBind(queueName, EXCHANGE_NAME, "route2");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
