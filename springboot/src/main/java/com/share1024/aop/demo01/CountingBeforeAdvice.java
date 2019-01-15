package com.share1024.aop.demo01;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/12
 */
public class CountingBeforeAdvice extends MethodCounter implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        count(method);
    }
}
