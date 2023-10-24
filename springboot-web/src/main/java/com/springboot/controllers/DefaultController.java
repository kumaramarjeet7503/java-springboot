package com.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping("/home")
	public String home() 
	{
		System.out.println("This is output page");
		return "index" ;
	}
	
		
}
