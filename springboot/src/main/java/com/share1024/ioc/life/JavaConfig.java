package com.share1024.ioc.life;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/16
 */
//@Configuration
public class JavaConfig {



    @Bean
    @Lazy
    public Person lazyPerson(){
        return new Person();
    }
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.scan("com.share1024.ioc.life");
        annotationConfigWebApplicationContext.refresh();
        Person student = (Person) annotationConfigWebApplicationContext.getBean("lazyPerson");


        annotationConfigWebApplicationContext.getBean("lazyPerson");
    }
}
