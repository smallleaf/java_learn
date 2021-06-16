package com.share1024.java.jvm.jconsole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2021/6/16 17:06
 * \* Description: -Xms100m -Xmx100m -XX:+UseSerialGC
 * \
 */
public class Jconsole {

    static class OOMObject{
        /**
         * 1M
         */
        public byte[] placeHolder = new byte[1024 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> oomObjects = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            TimeUnit.SECONDS.sleep(1);
            oomObjects.add(new OOMObject());
        }
//        //这里的gc是不会释放掉，oomObjects对象还存活，
//        System.out.println("开始 gc");
//        System.gc();
//        System.out.println("gc 结束");
//        TimeUnit.SECONDS.sleep(30);
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(70);
        //这里的gc会释放掉内存
        System.out.println("开始 gc");
        System.gc();
        System.out.println("gc 结束");
        TimeUnit.SECONDS.sleep(30);
    }
}