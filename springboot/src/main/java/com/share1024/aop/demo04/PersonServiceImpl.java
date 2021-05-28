package com.share1024.aop.demo04;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/15
 */
@Component
public class PersonServiceImpl implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory(this);
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("hei22heihei before");
            }
        });
        return proxyFactory.getProxy();
    }


    public void say(){
        System.out.println("=======jajaj===i am say====");
    }


    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
