package com.ly;

import com.ly.controller.dto.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public UserDto setMap(){
		UserDto user = new UserDto();
		user.setName("张三");
		user.setPwd("22222");
		return user;
	}
}
