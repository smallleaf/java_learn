package com.share1024.sort;

import org.junit.Test;

/**
 * @Description
 * @Date 2024年01月09日
 * @Created by yesheng
 */
public class StringRever {

    @Test
    public void test() {

        String words = "i am a student.";
        words = reverse(words);
        String[] wordSplit = words.split(" ");
        String result = "";
        for (String string : wordSplit) {
            result += reverse(string);
            result += " ";
        }
        result = result.substring(0,result.length() - 1);
        System.out.println(result);
    }



    public String reverse(String str) {
        int len = str.length();
        String reverStr = "";
        for (int i = 0; i < str.length(); i++) {
            reverStr+=str.charAt(len-i-1);
        }
        return reverStr;
    }
}
