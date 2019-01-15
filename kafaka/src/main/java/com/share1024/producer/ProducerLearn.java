package com.share1024.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-27
 */
public class ProducerLearn {



    private static final String TOPIC = "testJava";
    public static void main(String[] args) {
        Properties kafakaProperties = new Properties();
        //设置broker地址

        kafakaProperties.put("acks", "all");
        kafakaProperties.put("retries", 0);
        kafakaProperties.put("batch.size", 16384);
        kafakaProperties.put("linger.ms", 1);
        kafakaProperties.put("buffer.memory", 33554432);

        /**
         * 集群逗号配置多个
         */
        kafakaProperties.put("bootstrap.servers","172.16.168.200:9092");
        kafakaProperties.put("key.serializer", StringSerializer.class.getName());
        kafakaProperties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(kafakaProperties);

        ProducerRecord<String,String> record = new ProducerRecord<String,String>(TOPIC,"xxxxxx");

        producer.send(record,new ProducerCallBack());

        producer.flush();


    }

    static class ProducerCallBack implements Callback{

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            System.out.println("==="+metadata.toString());
        }
    }
}
