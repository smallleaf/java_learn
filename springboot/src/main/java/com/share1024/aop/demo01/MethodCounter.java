package com.share1024.aop.demo01;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/12
 */
public class MethodCounter {

    private HashMap<String,Integer> map = new HashMap<>();

    private int allCount;

    public void count(Method method){
        count(method.getName());
    }

    private void count(String methodName){
        Integer count = map.get(methodName);
        count = count == null ? 1:count++;
        map.put(methodName,count);
        allCount++;
    }

    private int getCalls(String methodName){
        Integer count = map.get(methodName);
        return count == null ? 0 : count;
    }

    public int getAllCount() {
        return allCount;
    }
}
