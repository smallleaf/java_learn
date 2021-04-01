package com.share1024.java.jvm.init;

/**
 * @author : yesheng
 * @Description :
 *
 * 结果  2
        3
        a=100,b=0
        1
        i am say

解释：类的初始化和实例化顺序。

1.在类的链接准备阶段会对static变量初始化为默认值，b=0，如果是static final 则会设置为指定的值。
2.初始化阶段，
    1.先去执行父类的<clinit>，再执行子类的,这个初始化方法做了:按照顺序执行static 变量个static代码块
    2.子类的<clinit>
    3.父类的成员变量，代码块
    4.父类的构造方法
    5.子类的成员变量，代码块
    6.子类的构造方法。

所有发生了如下事情：
1.在链接的准备阶段，b=0
2.执行 static  JvmTest jvmTest = new JvmTest();
3.实例化JvmTest，先成员变量，代码块，也就是输出2，再 a=100，注意这里的static int b = 112现在还未赋予，
    是因为在调用static  JvmTest jvmTest = new JvmTest();处于<clinit>阶段，static还没执行完。
  这里就是输出
    2
    3
    a=100,b=0
4.再执行System.out.println(1);，
5.        say();


=========================================

 * @Date : 2018/12/13
 */
public class JvmTest {

    public static void main(String[] args) {
        say();
    }

    /**
     * 先会进行初始化，这个阶段是Clinit初始化阶段，所以会直接进行new JvmTest() 也就是成员变量，构造函数
     */
    static  JvmTest jvmTest = new JvmTest();

    static {
        System.out.println(1);
    }
    int a = 100;

    {
        System.out.println(2 + "a:"+a);
    }

    JvmTest(){
        System.out.println(3);
        System.out.println("a="+a+",b="+b);

    }

    static int b = 112;


    public static void say(){
        System.out.println("i am say"+b);
    }

}
