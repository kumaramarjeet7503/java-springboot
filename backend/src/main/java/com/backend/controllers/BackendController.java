package com.backend.controllers;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entities.Person;
import com.backend.service.PersonService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BackendController {
    
    @Autowired
    private Environment env;

    @Autowired
    private PersonService personService ;

    @GetMapping("/get/welcome")
    public ResponseEntity<?> welcome()
    {
        String url = env.getProperty("backend.url") ;
        return ResponseEntity.ok("welcome") ;
    }

    
	@PostMapping("/post/welcome")
	public ResponseEntity<?> postWelcome( ) {
        		
		return ResponseEntity.ok("This is the post request") ;
	}

    @PostMapping("/create-person")
	public ResponseEntity<?> createPerson(@RequestBody Person person ) {
        		
        Person createdPerson = this.personService.createPerson(person) ;
		return ResponseEntity.ok(createdPerson) ;
	}
	

}
