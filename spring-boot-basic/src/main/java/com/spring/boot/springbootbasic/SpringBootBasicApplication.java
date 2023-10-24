package com.spring.boot.springbootbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBasicApplication {

	public static void main(String[] args) {
		System.out.println("hello spring");
		SpringApplication.run(SpringBootBasicApplication.class, args);
	}

}
