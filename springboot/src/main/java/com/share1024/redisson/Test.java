//package com.share1024.redisson;
//
//import com.share1024.redisson.support.DistributeLock;
//import org.redisson.api.RLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
//@Service
//public class Test {
//
//    @Autowired
//    private DistributeLock distributeLock;
//
//
//
//    @PostConstruct
//    public void test(){
//        RLock lock = distributeLock.getRedissonClient().getLock("test");
//        lock.lock();
//        System.out.println("+==");
//        lock.unlock();
//
////        distributeLock.lock("test",()->{
////            System.out.println("===");
////        });
//    }
//
//}
