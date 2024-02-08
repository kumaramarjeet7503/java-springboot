package com.exchangerateservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.exchangerateservice.entities.ExchangeResponse;

@FeignClient(name = "exchange-rate" ,url = "https://open.er-api.com")
public interface ExternalFeign {
    
    @GetMapping("/v6/latest/USD")
    public abstract ExchangeResponse getExchangeRate();
}
