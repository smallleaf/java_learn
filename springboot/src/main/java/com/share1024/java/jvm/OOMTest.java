package com.share1024.java.jvm;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-13
 */
public class OOMTest {

    private static final int MB = 1024 * 2 * 1024;
    public static void main(String[] args) {

        byte[] test = null;
        for(int i=0;i<100;i++){
            System.out.println(i);
            test = new byte[MB];
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
