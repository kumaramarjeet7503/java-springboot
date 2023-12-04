package com.contact.smartmanagerspringsecurity.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String processContact(@ModelAttribute("contact") Contact contact,@RequestParam("profileImage") MultipartFile file , Model model) throws IOException{
        User user = (User) model.getAttribute("user") ;
        contact.setUser(user);
        //  File uploading with file copy function
        if(!file.isEmpty())
        {
            contact.setImage(file.getOriginalFilename());
            File saveFile = new ClassPathResource("static/images").getFile() ;
            java.nio.file.Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename()) ;
            Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING) ;
        }

        user.getContacts().add(contact) ;
        this.userRepository.save(user) ;
        return "add_contact" ;
    }
}
