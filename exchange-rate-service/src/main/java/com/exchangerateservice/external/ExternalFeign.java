package com.exchangerateservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.exchangerateservice.entities.ExchangeResponse;


@FeignClient(name = "${exchange.name}" ,url = "${exchange.url}")
public interface ExternalFeign {
    
    @GetMapping("/USD")
    public abstract ExchangeResponse getExchangeRate();
}
