package com.share1024.guava;

import com.google.common.hash.Hashing;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @Description
 * @Date 2022年07月21日
 * @Created by yesheng
 */
public class HashTest {

    @Test
    public void md5(){
        System.out.println(Hashing.md5().hashString("test", Charset.forName("utf-8")));
        System.out.println(Hashing.sha1().hashString("test", Charset.forName("utf-8")));
        System.out.println(Hashing.sha512().hashString("test", Charset.forName("utf-8")));
        System.out.println(Hashing.sha256().hashString("test", Charset.forName("utf-8")));
    }
}
