package com.contact.smartmanagerspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.contact.smartmanagerspringsecurity.dao.UserRepository;
import com.contact.smartmanagerspringsecurity.entitity.Message;
import com.contact.smartmanagerspringsecurity.entitity.User;

import jakarta.servlet.http.HttpSession;

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

     @GetMapping("/login")
    public String login(Model m)
    {
        m.addAttribute("title","Smart Contact : Login") ;
        return "login" ;
    }

    @GetMapping("/signup")
    public String signup(Model m)
    {
        User user = new User() ;
        m.addAttribute("title","Smart Contact : Signup") ;
        m.addAttribute("user",user) ;
        return "signup" ;
    }

    // Registration process
        @PostMapping("/register")
    public String register(@ModelAttribute("user") User user,Model m, HttpSession session)
    {
        try{
        user.setEnabled(true);
        user.setRole("USER");
        // dfas ;
        this.userRepository.save(user);    
        // dfsa ;
        user = new User() ; 
        
        }catch(Exception e)
        {
            session.setAttribute("message",new Message("This is an error !"+e.getMessage(),"alert-danger"));
            e.printStackTrace();
            
        }
        m.addAttribute("user", user) ;  
        return "signup" ;
    }
}
