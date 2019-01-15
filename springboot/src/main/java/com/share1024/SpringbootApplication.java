package com.share1024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAspectJAutoProxy
@RestController
public class SpringbootApplication {


	@GetMapping("/test")
    public Object test(){
		Map<String,Object> test = new HashMap<>();
		test.put("userName","yesheng");
    	return test;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
