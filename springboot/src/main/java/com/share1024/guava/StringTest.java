package com.share1024.guava;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Bytes;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2022年07月21日
 * @Created by yesheng
 */
public class StringTest {


    @Test
    public void split(){
        List<Integer> ids = Splitter.on(",").trimResults().splitToStream("1, 2, 3, 4,5").map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(ids);
    }



}
