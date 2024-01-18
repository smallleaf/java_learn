package com.share1024.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2024年01月14日
 * @Created by yesheng
 */
public class Test2 {
    int N = 1010, mod = (int) 1e9 + 7;
    int[][] C = new int[N][N];

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        int size = test2.numOfWays(new int[] {3,4,5,1,2});
        System.out.println(size);
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;
        init(n);
        List<Integer> list = new ArrayList();
        for(int i = 0; i < n; i++) list.add(nums[i]);
        return f(list) - 1;
    }

    public int f(List<Integer> list){
        if(list.isEmpty()) return 1;
        List<Integer> left = new ArrayList();
        List<Integer> right = new ArrayList();
        int n = list.size();
        int r = list.get(0);
        for(int i = 1; i < n; i++){
            if(list.get(i) <= r) left.add(list.get(i));
            else right.add(list.get(i));
        }
        return (int)((long)C[n - 1][left.size()] * f(left) % mod * f(right) % mod);
    }

    //求组合数， 因为题目数据范围比较小
    public void init(int n){
        for(int a = 0; a <= n; a++){
            for(int b = 0; b <= a; b++){
                if(b == 0) C[a][b] = 1;
                else{
                    C[a][b] = (C[a - 1][b] + C[a - 1][b - 1]) % mod;
                }
            }
        }
    }

}
