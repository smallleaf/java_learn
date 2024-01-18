package com.share1024.sort;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2024年01月09日
 * @Created by yesheng
 */
public class CacheLru<K,V> extends LinkedHashMap<K,V> {
    private int maxSize;

    public CacheLru(int maxSize) {
        super(16,0.75f,true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.maxSize;
    }


}
