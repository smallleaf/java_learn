package com.share1024.sort;

import org.junit.Test;

/**
 * @Description
 * @Date 2024年01月09日
 * @Created by yesheng
 */
public class FN {

    public int getFn(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        return getFn(n-1) + getFn(n - 2);
    }

    public int getFn2(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        int f0 = 0;
        int f1 = 1;
        int result = 0;
        for(int i = 2;i<=n;i++) {
            result = f0 + f1;
            f0 = f1;
            f1 = result;
        }
        return result;
    }


    @Test
    public void testGetFn() {
        System.out.println(getFn(0));
        System.out.println(getFn(1));
        System.out.println(getFn(3));
        System.out.println(getFn2(3));
    }
}
