package com.share1024.java.jvm.hotswap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/13
 */
public class JavaClassHotExecutor {

    public static void execute(byte[] classByte){
        HotSwapClassLoader classLoader = new HotSwapClassLoader();
        Class clazz = classLoader.loadByte(classByte);
        try {
            Method method = clazz.getMethod("main",new Class[]{String[].class});
            method.invoke(null,new String[]{null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String path = "/Users/yesheng/Documents/软件开发/workspace/learnproject/springboot/target/classes/com/share1024/java/jvm/hotswap/HotTest.class";
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));

            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int len = 0;
            while((len = fileInputStream.read(buff,0,100))>0){
                byteArrayInputStream.write(buff,0,len);
            }
            execute(byteArrayInputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
