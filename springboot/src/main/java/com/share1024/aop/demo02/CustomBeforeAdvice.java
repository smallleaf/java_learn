package com.share1024.aop.demo02;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/14
 */
public class CustomBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("===ajjaajaj======");
    }
}
