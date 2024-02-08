package com.exchangerateservice.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerateservice.entities.ExchangeResponse;
import com.exchangerateservice.external.ExternalFeign;

@RestController
@RequestMapping("/exchange")
public class ExchangeRateController {

    @Autowired
    private ExternalFeign externalFeign ;
    
    @GetMapping("/get-rate")
    public ResponseEntity<Double> getExchangeRate(@RequestParam Double amount, @RequestParam String code)
    {
        ExchangeResponse response = this.externalFeign.getExchangeRate() ;
        HashMap<String, Double> rates =  response.getRates() ;
        Double codeExchangeRate = rates.get(code) * amount ;
        return ResponseEntity.ok(codeExchangeRate) ;
    }
}
