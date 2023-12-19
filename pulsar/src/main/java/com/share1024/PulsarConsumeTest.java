package com.share1024;

import org.apache.pulsar.client.api.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2023年08月27日
 * @Created by yesheng
 */
public class PulsarConsumeTest {

    @Test
    public void producerPartitionBatch() throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://pulsar3:6650")
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic("video/flow/enterRoom")
                .create();

        int i = 0;
        while(i<1000000) {
//            Thread.sleep(450);
            producer.send(("My message:"+System.currentTimeMillis()).getBytes());
            i++;
        }
        producer.close();
        client.close();
    }


    @Test
    public void consumer() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://pulsar1:6650")
                .build();

        System.out.println("==2=");

        Consumer<byte[]> consumer = client.newConsumer()
                .topic("video/flow/enterRoom")
                .subscriptionName("consumer1")
                .subscriptionType(SubscriptionType.Shared)
                .subscriptionInitialPosition(SubscriptionInitialPosition.Latest)
                .subscribe();

        System.out.println("===");

        while(true){
            Message<byte[]> message = consumer.receive();
            String msg = new String(message.getValue());
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println("Message received: " + msg);
            consumer.acknowledge(message);
        }
    }

    @Test
    public void consumer2() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Consumer<byte[]> consumer = client.newConsumer()
                .topic("test/app/topic4")
                .subscriptionName("consumer1")
                .subscriptionType(SubscriptionType.Shared)
                .negativeAckRedeliveryDelay(10, TimeUnit.MILLISECONDS)
                .subscriptionInitialPosition(SubscriptionInitialPosition.Latest)
                .subscribe();

        while(true){
            Message<byte[]> message = consumer.receive();
            String msg = new String(message.getValue());
            System.out.println("Message received: " + msg + ",retry count " + message.getRedeliveryCount());
            consumer.acknowledge(message);
        }

    }

}
