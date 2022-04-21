package com.sergax.springboot_restapi;

import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.UserServise;
import com.sergax.springboot_restapi.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestapiApplication.class, args);
	}
}
