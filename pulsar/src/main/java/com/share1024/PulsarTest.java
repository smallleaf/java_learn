package com.share1024;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.PartitionedProducerImpl;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2023年08月26日
 * @Created by yesheng
 */
public class PulsarTest {


    @Test
    public void producer() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic("public/default/topic3")
                .create();

        producer.send("My message1".getBytes());
        producer.close();
        client.close();
    }


    /**
     * 指定key，同一个key发到一个分区
     * @throws PulsarClientException
     */
    @Test
    public void producerPartition() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic("test/app/topic4")
                .messageRouter(new MessageRouter() {
                    @Override
                    public int choosePartition(Message<?> msg, TopicMetadata metadata) {
                        return 0;
                    }
                })
//                .intercept(new CustomPulsarIntercept())
                .create();

        for (int i = 15000; i < 20000; i++) {
            producer.send((""+i).getBytes());
        }
        producer.close();
        client.close();
    }


    /**
     * 指定key，同一个key发到一个分区
     * @throws PulsarClientException
     */
    @Test
    public void producerPartitionBatch() throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic("test/app/topic4")
                .messageRouter(new MessageRouter() {
                    @Override
                    public int choosePartition(Message<?> msg, TopicMetadata metadata) {
                        return 0;
                    }
                })
                .intercept(new CustomPulsarIntercept())
                .create();

        int i = 0;
        while(i<100000) {
            Thread.sleep(1000);
            producer.send(("My message:"+System.currentTimeMillis()).getBytes());
            i++;
        }
        producer.close();
        client.close();
    }

    @Test
    public void consumer2() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Consumer<byte[]> consumer = client.newConsumer()
                .topic("test/app/topic4")
                .subscriptionName("consumer2")
                .subscriptionType(SubscriptionType.Exclusive)
                .negativeAckRedeliveryDelay(10, TimeUnit.MILLISECONDS)
                .subscriptionInitialPosition(SubscriptionInitialPosition.Latest)
                .subscribe();

        while(true){
            Message<byte[]> message = consumer.receive();
            String msg = new String(message.getValue());
            System.out.println("Message received: " + msg + ",retry count " + message.getRedeliveryCount());
            if(Integer.parseInt(msg) > 10000) {
                consumer.acknowledge(message);
            }
        }

    }

    @Test
    public void consumer() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Consumer<byte[]> consumer = client.newConsumer()
                .topic("public/default/topic3")
                .subscriptionName("consumer1")
                .subscriptionType(SubscriptionType.Exclusive)
                .negativeAckRedeliveryDelay(10, TimeUnit.MILLISECONDS)
                .subscriptionInitialPosition(SubscriptionInitialPosition.Latest)
                .subscribe();

        while(true){
            Message<byte[]> message = consumer.receive();
            String msg = new String(message.getValue());
            System.out.println("Message received: " + msg + ",retry count " + message.getRedeliveryCount());
            if(Integer.parseInt(msg) > 10000) {
                consumer.acknowledge(message);
            }
        }
    }



}
