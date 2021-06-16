package com.share1024.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

public class Consumer {

    public int id = 1;

    public static void main(String[] args) {
        Properties kafakaProperties = new Properties();
        kafakaProperties.put("bootstrap.servers","localhost:9092");
        kafakaProperties.put("key.deserializer", StringDeserializer.class.getName());
        kafakaProperties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        kafakaProperties.put("group.id","test");
        kafakaProperties.put("auto.commit.offset",false);
        kafakaProperties.put("enable.auto.commit",false);

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(kafakaProperties);

        consumer.subscribe(Collections.singletonList("testJava"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
                Map<TopicPartition, OffsetAndMetadata> offset = new HashMap<>();
                offset.put(new TopicPartition(record.topic(),record.partition()),
                        new OffsetAndMetadata(record.offset()+1,"test"));
                consumer.commitAsync(offset, (offsets, exception) -> {

                });
            }
            consumer.commitAsync();
        }
    }
}
