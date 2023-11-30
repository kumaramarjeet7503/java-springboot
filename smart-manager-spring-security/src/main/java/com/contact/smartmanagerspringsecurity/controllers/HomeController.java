package com.contact.smartmanagerspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contact.smartmanagerspringsecurity.dao.UserRepository;
import com.contact.smartmanagerspringsecurity.entitity.User;

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

    @GetMapping("/home")
    public String home(Model m)
    {
        m.addAttribute("title","Smart Contact : Home") ;
        return "home" ;
    }

        @GetMapping("/about")
    public String about(Model m)
    {
        m.addAttribute("title","Smart Contact : About") ;
        return "about" ;
    }
}
