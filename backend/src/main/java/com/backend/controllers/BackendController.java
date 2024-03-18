package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BackendController {
    
    @Autowired
    private Environment env;

    @GetMapping("/get/welcome")
    public String welcome()
    {
        String url = env.getProperty("backend.url") ;
        return url ;
    }

    
	@PostMapping("/post/welcome")
	public String postMethodName( ) {
        		
		return "This is the post request";
	}
	

}
