package com.share1024.sort;

/**
 * @Description
 * @Date 2024年01月09日
 * @Created by yesheng
 */
public class Base62Converter {
    private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String longToBase62(long longNumber) {
        if (longNumber == 0) {
            return String.valueOf(CHARSET.charAt(0));
        }

        //longNumber只取62位
        StringBuilder base62 = new StringBuilder();
        int size = 0;
        while (longNumber > 0) {
            int remainder = (int) (longNumber % 62);
            base62.insert(0, CHARSET.charAt(remainder));
            longNumber /= 62;
            if(++size > 6){
                break;
            }
        }

        return base62.toString();
    }

    public static void main(String[] args) {
        //雪花算法

        long longNumber = System.currentTimeMillis();
        String shortCode = longToBase62(longNumber);

        System.out.println("Long Number: " + longNumber);
        System.out.println("Short Code: " + shortCode);
    }
}
