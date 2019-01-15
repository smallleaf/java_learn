package com.share1024.threadpool;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/12
 */
@Service
public class ThreadService implements InitializingBean{

    @Autowired
    @Qualifier("concurrentTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private TaskScheduler threadPoolTaskScheduler;

    @Autowired
    private AsyncService asyncService;


    public void testThreadPoolTaskExecutor(){
        taskExecutor.execute(new ProductThread());
    }

    public void testThreadPoolTaskScheduler(){
        Instant instant = Instant.now().plusSeconds(6);
        threadPoolTaskScheduler.scheduleAtFixedRate(new ProductThread(),instant, Duration.ofSeconds(6));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        asyncService.test();
//        for(int i= 0;i< 50;i++){
//            testThreadPoolTaskExecutor();
//        }
    }
}
