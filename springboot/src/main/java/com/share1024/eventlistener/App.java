package com.share1024.eventlistener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@EnableAsync
public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext("com.share1024.eventlistener");
		ConsumerService consumerService = annotationConfigApplicationContext.getBean("consumerService",
				ConsumerService.class);
		Map<String,Object> test = new HashMap<>();
		test.put("userName","yesheng");
		consumerService.test("test");
		System.out.println("===");
	}
}
