package com.share1024.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        Properties kafakaProperties = new Properties();
        kafakaProperties.put("bootstrap.servers","localhost:9092");
        kafakaProperties.put("key.deserializer", StringDeserializer.class.getName());
        kafakaProperties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        kafakaProperties.put("group.id","test");
        kafakaProperties.put("auto.commit.offset",false);
        kafakaProperties.put("enable.auto.commit",false);


        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(kafakaProperties);

        consumer.subscribe(Collections.singletonList("testJava"));

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
            consumer.commitSync();
        }
    }
}
