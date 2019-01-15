package com.share1024.threadpool;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/12
 */
@Service
public class AsyncService {


    @Async
    public void test(){
        try {
            System.out.println("===========");
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(initialDelay = 6000,fixedDelay = 6000 )
    public void scheduled(){
        System.out.println("====");
    }
}
