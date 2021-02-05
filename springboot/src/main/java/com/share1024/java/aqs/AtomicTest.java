package com.share1024.java.aqs;

import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

/**
 * \* @Author: yesheng
 * \* Date: 2021/1/21 15:23
 * \* Description:
 * \
 */
public class AtomicTest {
    public int i = 1;

    public static void main(String[] args) {

//        AtomicTest atomicTest = new AtomicTest();
//        AtomicTest atomicTest2 = new AtomicTest();
//        atomicTest2.i = 2;
//        AtomicReference<AtomicTest> atomicReference = new AtomicReference<>(atomicTest);
//        System.out.println(atomicReference.compareAndSet(atomicTest,atomicTest2));
//        System.out.println(atomicReference.get().i);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        atomicIntegerArray.addAndGet(1,1);
    }
}