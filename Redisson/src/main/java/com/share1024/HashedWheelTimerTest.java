package com.share1024;

import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {

    public static void main(String[] args) {
        HashedWheelTimer timer = new HashedWheelTimer(new DefaultThreadFactory("redisson-timer"), 1, TimeUnit.MILLISECONDS, 1, false);
        timer.newTimeout((timeout)->{
            System.out.println("===");
        },100,TimeUnit.SECONDS);

        timer.newTimeout((timeout)->{
            System.out.println("===");
        },100,TimeUnit.SECONDS);

    }
}
