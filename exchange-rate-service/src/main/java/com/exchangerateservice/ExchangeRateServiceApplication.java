package com.exchangerateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.exchangerateservice.config.ExchangeProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(ExchangeProperties.class)
public class ExchangeRateServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateServiceApplication.class, args);
	}

}
