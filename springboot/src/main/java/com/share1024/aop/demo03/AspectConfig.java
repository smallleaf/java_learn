package com.share1024.aop.demo03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/15
 */
//@Aspect
//@Component
public class AspectConfig {

    @Pointcut("execution(* com.share1024.aop.demo03.IPerson.*(..))")
    private void pointCut(){}


    @Before("pointCut()")
    public void before(){
        System.out.println(" i am before");
    }

    @After("pointCut()")
    public void after(){
        System.out.println(" i am after");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("around before");

        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after");

    }
}
