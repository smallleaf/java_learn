package com.share1024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.share1024.cat")
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		springApplication.run(SpringbootApplication.class, args);
	}
}
