package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    
    @Autowired
    private Environment env;

    @GetMapping("/welcome")
    public String welcome()
    {
        String url = env.getProperty("backend.url") ;
        return url ;
    }

}
