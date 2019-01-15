package com.share1024.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-27
 */
public class ConsumerLearnn2 {

    private static final String TOPIC = "test";

    public static void main(String[] args) {

        Properties kafakaProperties = new Properties();
        //设置broker地址
        kafakaProperties.put("group.id", "jd-group");
        kafakaProperties.put("enable.auto.commit", "true");
        kafakaProperties.put("auto.commit.interval.ms", "1000");
        kafakaProperties.put("bootstrap.servers","pbasadvw02.rmz.gomo.com:9092");
        kafakaProperties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        kafakaProperties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(kafakaProperties);
        kafkaConsumer.subscribe(Arrays.asList(TOPIC));

        while(true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }

    }
}
