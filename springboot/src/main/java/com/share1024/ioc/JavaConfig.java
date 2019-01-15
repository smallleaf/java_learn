package com.share1024.ioc;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/16
 */
public class JavaConfig {



    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.scan("com.share1024.ioc");
        annotationConfigWebApplicationContext.refresh();
        Student student = (Student) annotationConfigWebApplicationContext.getBean("student");
        student.say();
    }
}
