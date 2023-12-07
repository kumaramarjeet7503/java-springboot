package com.contact.smartmanagerspringsecurity.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.contact.smartmanagerspringsecurity.dao.ContactRepository;
import com.contact.smartmanagerspringsecurity.dao.UserRepository;
import com.contact.smartmanagerspringsecurity.entitity.Contact;
import com.contact.smartmanagerspringsecurity.entitity.User;

@RestController
public class SearchController {
    
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private ContactRepository contactRepository ;

    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchContact(@PathVariable("query") String query , Principal principal)
    {
        String name = principal.getName() ;
        User user = this.userRepository.getUserByUserName(name) ; 
        List<Contact> contact = this.contactRepository.findContactByNameContainingAndUser(query, user) ;
        return ResponseEntity.ok(contact) ;
    }
}
