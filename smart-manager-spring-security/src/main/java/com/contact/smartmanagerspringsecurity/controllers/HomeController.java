package com.contact.smartmanagerspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.contact.smartmanagerspringsecurity.dao.UserRepository;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository ;

    @GetMapping("/create-user")
    public String createUser()
    {
        User user = new User() ;
        user.setName("Amarjeet");
        user.setPassword("falkda");
        userRepository.save(user) ;
        return "welcome" ;
    }

}
