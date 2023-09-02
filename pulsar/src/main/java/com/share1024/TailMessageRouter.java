package com.share1024;

import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageRouter;
import org.apache.pulsar.client.api.TopicMetadata;

/**
 * @Description
 * @Date 2023年08月26日
 * @Created by yesheng
 */
public class TailMessageRouter implements MessageRouter {
    @Override
    public int choosePartition(Message<?> msg, TopicMetadata metadata) {
        return 1;
    }
}
