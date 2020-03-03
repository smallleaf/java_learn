package com.share1024;

import com.share1024.eventlistener.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringbootApplication {


	@Autowired
	private ConsumerService consumerService;
	@GetMapping("/test")
    public Object test(){
		Map<String,Object> test = new HashMap<>();
		test.put("userName","yesheng");
		consumerService.test("test");
    	return test;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
