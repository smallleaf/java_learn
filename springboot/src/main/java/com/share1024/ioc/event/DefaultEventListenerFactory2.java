package com.share1024.ioc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationListenerMethodAdapter;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

public class DefaultEventListenerFactory2 implements EventListenerFactory, Ordered {

    private int order = LOWEST_PRECEDENCE;


    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }


    @Override
    public boolean supportsMethod(Method method) {
        return true;
    }

    @Override
    public ApplicationListener<?> createApplicationListener(String beanName, Class<?> type, Method method) {
        return new ApplicationListenerMethodAdapter2(beanName, type, method);
    }

}
