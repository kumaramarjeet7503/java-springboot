package com.spring.boot.springbootbasic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class DefaultController {

	@RequestMapping("/default")
	public String home() 
	{
		
		return "This is the default page" ;
	}
	
}
