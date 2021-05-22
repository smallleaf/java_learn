package com.share1024;

import com.share1024.iocCircle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.share1024.iocCircle")
public class SpringbootApplication {

	@Autowired
	private User user;

	@Value("${spring.profiles.active}")
	private String evn;

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(SpringbootApplication.class, args);
	}
}
