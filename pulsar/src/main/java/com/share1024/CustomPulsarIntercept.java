package com.share1024;

import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.interceptor.ProducerInterceptor;

/**
 * @Description
 * @Date 2023年08月27日
 * @Created by yesheng
 */
public class CustomPulsarIntercept implements ProducerInterceptor {
    @Override
    public void close() {

    }

    @Override
    public boolean eligible(Message message) {
        System.out.println("=========eligible==========");
        return true;
    }

    @Override
    public Message beforeSend(Producer producer, Message message) {
        return message;
    }

    @Override
    public void onSendAcknowledgement(Producer producer, Message message, MessageId msgId, Throwable exception) {

    }
}
