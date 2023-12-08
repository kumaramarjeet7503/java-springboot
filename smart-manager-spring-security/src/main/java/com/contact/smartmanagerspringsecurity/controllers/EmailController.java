package com.contact.smartmanagerspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contact.smartmanagerspringsecurity.request.EmailRequest;


@RestController
public class EmailController {

    @Autowired
    private com.contact.smartmanagerspringsecurity.service.EmailService emailService ;
    
    @PostMapping("send/email")
    public ResponseEntity<?> email(@RequestBody EmailRequest emailRequest)
    {
        this.emailService.sendEmail( emailRequest.getSubject() , emailRequest.getMessage() , emailRequest.getTo()) ;
        return ResponseEntity.ok("done");
    }
}
