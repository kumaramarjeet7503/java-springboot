package com.springbootjunit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootjunit.dao.StudentRepository;


@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository ;
    
    @GetMapping("get-tested")
    public ResponseEntity getMethodName() {

        studentRepository.findAll() ;

        return ResponseEntity.ok("Tested well") ;
    }
    
}
