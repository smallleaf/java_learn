package com.share1024.sort;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @Description
 * @Date 2024年01月09日
 * @Created by yesheng
 */
public class KthLargest {
    private PriorityQueue<Integer> minHeap;

    private int size;



    public KthLargest(int k,int[] nums) {
        this.minHeap = new PriorityQueue<>((a,b)-> b - a);
        size = k;
        for (int num : nums) {
            minHeap.offer(num);
        }
    }

    public int add(int val) {
        if(this.minHeap.size() < size) {
            this.minHeap.offer(val);
        }else if(val > this.minHeap.peek()) {
            this.minHeap.poll();
            this.minHeap.offer(val);
        }
        return this.minHeap.peek();
    }

    public static List<Integer> findMaxCount(int k,List<Integer> input) {
        Map<Integer,Integer> numCountMap = new HashMap<>();
        for (Integer i : input) {
            numCountMap.put(i,numCountMap.getOrDefault(i,0) + 1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> minHeapQueue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            if(minHeapQueue.size() < k) {
                minHeapQueue.add(entry);
            }else if (entry.getValue() > minHeapQueue.peek().getValue()) {
                minHeapQueue.poll();
                minHeapQueue.add(entry);
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!minHeapQueue.isEmpty()) {
            result.add(minHeapQueue.poll().getKey());
        }
        return result;
     }

    public static void main(String[] args) {
        List<Integer> result = findMaxCount(2, Lists.newArrayList(1,3,4,2,1,2));
        System.out.println("===");
    }
}
