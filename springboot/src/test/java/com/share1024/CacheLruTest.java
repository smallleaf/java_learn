package com.share1024;

import com.share1024.sort.CacheLru;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @Description
 * @Date 2024年01月18日
 * @Created by yesheng
 */
public class CacheLruTest {


    @Test
    public void shouldEvictLeastRecentlyUsedWhenMaxSizeExceeded() {
        CacheLru<String, String> lruCache = new CacheLru<>(3);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        lruCache.put("4", "4"); // This should evict "1"
        assertNull(lruCache.get("1"));
    }

    @Test
    public void shouldNotEvictItemsIfMaxSizeNotExceeded() {
        CacheLru<String, String> lruCache = new CacheLru<>(3);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        assertEquals("1", lruCache.get("1"));
    }

    @Test
    public void shouldUpdateLeastRecentlyUsedOnAccess() {
        CacheLru<String, String> lruCache = new CacheLru<>(3);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        lruCache.get("1"); // This should update "1" to be the most recently used
        lruCache.put("4", "4"); // This should evict "2"
        assertNull(lruCache.get("2"));
    }
}
