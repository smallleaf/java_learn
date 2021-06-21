package com.share1024.java.jvm.distribution;

import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2021/6/17 12:02
 * \* Description:
 *
 * \
 */
public class OldSpace {

    private static final int _1MB = 1024 * 1024;
    //-verbose:gc -Xms40M -Xmx40M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseConcMarkSweepGC
    public static void testBigObject() throws InterruptedException {
        byte[] test1,test2,test3,test4;
        TimeUnit.SECONDS.sleep(30);
        test1 = new byte[4 * _1MB];
        System.out.println(1);
        TimeUnit.SECONDS.sleep(10);
        test2 = new byte[4 * _1MB];
        System.out.println(2);
        TimeUnit.SECONDS.sleep(10);
        test3 = new byte[4 * _1MB];
        System.out.println(3);
        TimeUnit.SECONDS.sleep(10);
        test4 = new byte[4 * _1MB];
        System.out.println(4);
        TimeUnit.SECONDS.sleep(10);
    }

    public static void main(String[] args) throws InterruptedException {
        testBigObject();
    }
}