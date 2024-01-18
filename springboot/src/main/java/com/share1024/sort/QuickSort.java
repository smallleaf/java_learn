package com.share1024.sort;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * @Description
 * @Date 2024年01月06日
 * @Created by yesheng
 */
public class QuickSort {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        List<List<Integer>> test = new ArrayList<>();
        test.add(new ArrayList<>(stack));
        System.out.println("===");
    }


    public void sort(List<Integer> list,int left,int right) {
        if(left >= right) {
            return;
        }
        //获得中间位置
        int partition = partition(list,left,right);
        //遍历左
        sort(list,left,partition -1);
        //处理右
        sort(list,partition +1 ,right);
    }

    public int partition(List<Integer> subList,int left,int right) {
        int partition = subList.get(left);
        while(left < right) {
            while(left < right && subList.get(right) >= partition) {
                right --;
            }
            if(left < right) {
                int rightValue = subList.get(right);
                subList.set(left,rightValue);
            }
            while(left < right && subList.get(left) <= partition) {
                left++;
            }
            if(left < right) {
                int leftValue = subList.get(left);
                subList.set(right,leftValue);
            }
        }
        subList.set(left,partition);
        return left;
    }



}
