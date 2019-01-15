package com.share1024.java.jvm;

import java.io.InputStream;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/13
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null)
                        return super.loadClass(name);
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (Exception e){
                    throw  new ClassNotFoundException();
                }
            }
        };
        try {
            Object obj  =  myLoader.loadClass("com.share1024.java.jvm.ClassLoaderTest").newInstance();
            System.out.println(obj.getClass().getClassLoader());
            System.out.println(obj instanceof com.share1024.java.jvm.ClassLoaderTest);

            //返回false，因为是不同的类加载器加载的
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
