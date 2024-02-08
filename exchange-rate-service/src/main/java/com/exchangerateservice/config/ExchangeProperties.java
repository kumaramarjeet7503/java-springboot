package com.exchangerateservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix= "exchange")
public class ExchangeProperties {
    private String url ;
    private String name ;
}
