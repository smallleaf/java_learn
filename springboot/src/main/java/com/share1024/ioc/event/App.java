package com.share1024.ioc.event;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;

@Import(YouhuaEventListener.class)
public class App {

    @Autowired
    private ApplicationEventPublisher  eventPublisher;

    @EventListener(TestEvent.class)
    public void event(){
        System.out.println("event");
    }

    @PostConstruct
    public void init(){
        System.out.println("init");
        eventPublisher.publishEvent(new TestEvent());
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(App.class);
        annotationConfigApplicationContext.publishEvent(new TestEvent());
    }
}
