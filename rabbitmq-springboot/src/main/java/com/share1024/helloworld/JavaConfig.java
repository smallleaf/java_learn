package com.share1024.helloworld;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/11
 */
public class JavaConfig {

//    @Bean
//    public Queue queue(){
//        return new Queue("springboot");
//    }

//    @Bean
//    public Queue autoDeleteQueue1() {
//        return new Queue("autoDeleteQueue1",true);
//    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new Queue("autoDeleteQueue2",true);
    }

    @Bean
    public Binding binding1(DirectExchange direct ,
                            Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("key1");
    }

    /**
     * 延迟队列 TTL 名称
     */
    private static final String REGISTER_DELAY_QUEUE = "dev.book.register.delay.queue";
    public static final String REGISTER_EXCHANGE_NAME = "dev.book.register.exchange";
    public static final String ROUTING_KEY = "all";

    @Bean
    public Queue autoDeleteQueue1() {
        Map<String, Object> params = new HashMap<>();
//        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
//        params.put("x-dead-letter-exchange", REGISTER_EXCHANGE_NAME);
//        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
//        params.put("x-dead-letter-routing-key", ROUTING_KEY);
        return new Queue("autoDeleteQueue1");
    }


    @Bean
    public Binding binding2(DirectExchange direct,
                            Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("key2");
    }

//    @Bean
//    public FanoutExchange fanout(){
//        return new FanoutExchange("tut.fanout");
//    }

    @Bean
    public DirectExchange direct(){
        DirectExchange direct = new DirectExchange("ye.direct");
        direct.setDelayed(true);
        return direct;
    }

//    @Bean
//    public TopicExchange topic(){
//        TopicExchange topic = new TopicExchange("ye.topic2");
//        topic.setDelayed(true);
//        return topic;
//    }
    @Bean
    public Receiver receiver(){
        return new Receiver("yesheng1");
    }

//    @Bean
//    public Receiver receiver2(){
//        return new Receiver("yesheng2");
//    }

    @Bean
    public Sender sender(){
        return new Sender();
    }
}
