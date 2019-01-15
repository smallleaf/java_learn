package com.share1024.java.util;

import org.junit.Test;

import java.util.WeakHashMap;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/22
 */
public class MapTest {

    @Test
    public void weakHashMap(){
        WeakHashMap<String,String> map = new WeakHashMap<>();
        map.put("yesheng","hjahaha");
        System.gc();
    }
}
