package com.share1024.ioc.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationListenerMethodAdapter;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

public class YouhuaEventListener implements InstantiationAwareBeanPostProcessor , ApplicationContextAware, BeanFactoryPostProcessor {

    private BeanFactory beanFactory;
    @Nullable
    private List<EventListenerFactory> eventListenerFactories;

    @Nullable
    private ConfigurableApplicationContext applicationContext;

    private EventExpressionEvaluator eventExpressionEvaluator = new EventExpressionEvaluator();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class targetType = bean.getClass();
        Map<Method, EventListener> annotatedMethods = null;
        try {
            annotatedMethods = MethodIntrospector.selectMethods(targetType,
                    (MethodIntrospector.MetadataLookup<EventListener>) method ->
                            AnnotatedElementUtils.findMergedAnnotation(method, EventListener.class));
        }
        catch (Throwable ex) {
            // An unresolvable type in a method signature, probably from a lazy bean - let's ignore it.
        }

        if (CollectionUtils.isEmpty(annotatedMethods)) {
            return bean;
        }
        else {
            // Non-empty set of methods
            List<EventListenerFactory> factories = this.eventListenerFactories;
            Assert.state(factories != null, "EventListenerFactory List not initialized");
            for (Method method : annotatedMethods.keySet()) {
                for (EventListenerFactory factory : factories) {
                    if (factory.supportsMethod(method)) {
                        Method methodToUse = AopUtils.selectInvocableMethod(method, beanFactory.getType(beanName));
                        ApplicationListener<?> applicationListener =
                                factory.createApplicationListener(beanName, targetType, methodToUse);
                        if (applicationListener instanceof ApplicationListenerMethodAdapter2) {
                            ((ApplicationListenerMethodAdapter2) applicationListener).init(applicationContext,eventExpressionEvaluator);
                        }
                        applicationContext.addApplicationListener(applicationListener);
                        break;
                    }
                }
            }
        }
        return bean;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        this.eventListenerFactories = Arrays.asList(new DefaultEventListenerFactory2());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Assert.isTrue(applicationContext instanceof ConfigurableApplicationContext,
                "ApplicationContext does not implement ConfigurableApplicationContext");
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;    }
}
