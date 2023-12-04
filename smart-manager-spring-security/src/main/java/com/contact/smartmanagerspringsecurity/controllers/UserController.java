package com.contact.smartmanagerspringsecurity.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model ;
import com.contact.smartmanagerspringsecurity.dao.UserRepository;
import com.contact.smartmanagerspringsecurity.entitity.Contact;
import com.contact.smartmanagerspringsecurity.entitity.User;


@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository ;

    @ModelAttribute
    public void getUser(Model model,Principal principal)
    {
        String userName = principal.getName() ;
        User user = this.userRepository.getUserByUserName(userName) ;
        model.addAttribute("user",user) ;
    }

    @RequestMapping("index")
    public String index(org.springframework.ui.Model model,Principal principal)
    {
        model.addAttribute("title", "dashboard") ;
        return "user_dashboard" ;
    }

    @RequestMapping("add-contact")
    public String addContact(org.springframework.ui.Model model)
    {
        Contact contact = new Contact() ;
        model.addAttribute("title", "Add contact") ;
        model.addAttribute("contact", contact) ;
        return "add_contact" ;
    }

    @PostMapping("process-contact")
    public String processContact(@ModelAttribute("contact") Contact contact, Model model){
        User user = (User) model.getAttribute("user") ;
        contact.setUser(user);
        user.getContacts().add(contact) ;
        this.userRepository.save(user) ;
        return "add_contact" ;
    }
}
