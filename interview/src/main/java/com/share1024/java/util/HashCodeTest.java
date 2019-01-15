package com.share1024.java.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/21
 */
public class HashCodeTest {

    public static void main(String[] args) {

        Map<MapKey,MapValue>  map = new HashMap<>();
        MapKey mapKey = new MapKey(10);
        MapValue mapValue = new MapValue(10);
        map.put(mapKey,mapValue);

        MapKey queryKey = new MapKey(10);
        MapValue result = map.get(queryKey);
        //未加hashcode，equals返回的为空，因为我们两次key生成的散列值是不同的
        //hashcode必须和equals一起才行
        //hashcode是表示相同的散列值，equals表示当前的建是否与表中存在相同的键

        //仔细看源码会发现，

        System.out.println(result);
    }


    static class MapKey{
        int value ;

        public MapKey(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MapKey mapKey = (MapKey) o;

            return value == mapKey.value;
        }
    }

    static class MapValue{
        int value;

        public MapValue(int value) {
            this.value = value;
        }
        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MapValue mapValue = (MapValue) o;

            return value == mapValue.value;
        }
    }
}
