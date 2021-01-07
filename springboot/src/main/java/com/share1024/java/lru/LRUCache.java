package com.share1024.java.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * \* @Author: yesheng
 * \* Date: 2020/11/16 12:04
 * \* Description:
 * \
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final int MAX_CAHCE_SIZE;

    public LRUCache(int cacheSize){
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.MAX_CAHCE_SIZE  = cacheSize;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CAHCE_SIZE;
    }


    public static void main(String[] args) {
        LRUCache<String,String> cache = new LRUCache<>(2);
        cache.put("test","test2");
        cache.put("test2","test3");
        System.out.println("===");
        cache.get("test2");
        cache.put("test3","g");
        System.out.println("===");
    }

}