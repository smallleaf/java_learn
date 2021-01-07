package com.share1024.java.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * \* @Author: yesheng
 * \* Date: 2021/1/7 15:55
 * \* Description:
 * \
 */
public class UnsafeTest {

    public  Object test;
    public  Object test2;
    private static long testOffset;
    private static long testOffset2;
    static Unsafe unsafe = null;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            testOffset =  unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("test"));
            testOffset2 =  unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("test2"));

            System.out.println(testOffset);
            System.out.println(testOffset2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean casObject(Object expect,Object update){
        return unsafe.compareAndSwapObject(this,testOffset,expect,update);
    }

    public static void main(String[] args) {
        UnsafeTest unsafeTest = new UnsafeTest();
        Object t = new Object();
        unsafeTest.test = t;
        Object t2 = new Object();
        System.out.println(unsafeTest.casObject(t,t2));
        System.out.println(unsafeTest.casObject(t,t2));
    }

}