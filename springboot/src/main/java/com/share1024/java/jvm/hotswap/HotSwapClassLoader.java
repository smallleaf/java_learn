package com.share1024.java.jvm.hotswap;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/13
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader(){
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }
}
