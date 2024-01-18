package com.share1024.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-27
 */
public class ProducerLearn2 {

    private static final String TOPIC = "testJava";
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties kafakaProperties = new Properties();
        //设置broker地址

        kafakaProperties.put("acks", "all");
        kafakaProperties.put("retries", 1);
        kafakaProperties.put("batch.size", 16384);
        kafakaProperties.put("linger.ms", 1);
        kafakaProperties.put("buffer.memory", 33554432);
        kafakaProperties.put("transactional.id", "my-transactional-id"); // 设置事务ID


        /**
         * 集群逗号配置多个
         */
        kafakaProperties.put("bootstrap.servers","172.16.9.24:9092");
        kafakaProperties.put("key.serializer", StringSerializer.class.getName());
        kafakaProperties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(kafakaProperties);

        producer.initTransactions();
        try {
            producer.beginTransaction();
            ProducerRecord<String,String> record = new ProducerRecord<String,String>(TOPIC,"hahah","test");
            producer.send(record,new ProducerCallBack());

            ProducerRecord<String,String> record2 = new ProducerRecord<String,String>(TOPIC,"hahah","test2");
            producer.send(record2,new ProducerCallBack());

            producer.commitTransaction();
        }catch (Exception e) {
            producer.abortTransaction();
        }

        producer.flush();


    }

    static class ProducerCallBack implements Callback{

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            System.out.println("==="+metadata.toString());
        }
    }
}
