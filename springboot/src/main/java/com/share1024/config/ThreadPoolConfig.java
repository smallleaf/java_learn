package com.share1024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executors;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/12
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ThreadPoolConfig {


    /**
     * ThreadPoolTaskExecutor
     * 对应java.util.concurrent.ThreadPoolExecutor
     * @return
     */
    @Bean
    public TaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(7);
        taskExecutor.setKeepAliveSeconds(1000*60*60);
        return taskExecutor;
    }


    @Bean
    public TaskExecutor concurrentTaskExecutor(){
        ConcurrentTaskExecutor taskExecutor = new ConcurrentTaskExecutor(Executors.newFixedThreadPool(10));
        return taskExecutor;
    }


    @Bean
    public TaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setAwaitTerminationSeconds(1000*6);
        return taskScheduler;

    }
}
