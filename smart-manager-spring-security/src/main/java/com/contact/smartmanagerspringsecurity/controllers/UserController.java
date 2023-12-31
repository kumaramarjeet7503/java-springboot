package com.contact.smartmanagerspringsecurity.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model ;

import com.contact.smartmanagerspringsecurity.dao.ContactRepository;
import com.contact.smartmanagerspringsecurity.dao.UserRepository;
import com.contact.smartmanagerspringsecurity.entitity.Contact;
import com.contact.smartmanagerspringsecurity.entitity.Message;
import com.contact.smartmanagerspringsecurity.entitity.User;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository ;
        @Autowired
    private ContactRepository contactRepository ;

     @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

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
    public String processContact(@ModelAttribute("contact") Contact contact,@RequestParam("profileImage") MultipartFile file , Model model, HttpSession session) throws IOException{
       
        try{
            User user = (User) model.getAttribute("user") ;
            contact.setUser(user);
            contact.setImage("contact.png") ;
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
            session.setAttribute("message",new Message("Contact Added succesfully !!", "alert-success")) ;
        }catch(Exception e)
        {
            session.setAttribute("message",new Message("Contact Added succesfully !!", "alert-danger")) ;
            e.printStackTrace();

        }
        return "add_contact" ;
    }

    @GetMapping("/view-contact/{page}")
    public String viewContact(@PathVariable("page") Integer page  ,Model model){

        //  Set pagination 
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page,5) ;

        User user = (User) model.getAttribute("user") ;
        org.springframework.data.domain.Page<Contact> contacts =  this.contactRepository.getContactsByUserId(user.getId(),pageable) ;
        
        model.addAttribute("contacts",contacts) ;
        model.addAttribute("currentPage",page) ;
        model.addAttribute("totalPage", contacts.getTotalPages()) ;
        return "view_contact" ;
    }

        @GetMapping("/brief-contact/{cId}")
    public String briefContact(@PathVariable("cId") Integer cId  ,Model model){

        Optional<Contact> optContact = this.contactRepository.findById(cId) ;
        Contact  contact = optContact.get() ;

        model.addAttribute("contact", contact) ;
        User user = (User) model.getAttribute("user") ;

        return "brief_contact" ;
    }

            @GetMapping("/delete-contact/{cId}")
    public String deleteContact(@PathVariable("cId") Integer cId  ,Model model, HttpSession session ){

        Optional<Contact> optContact = this.contactRepository.findById(cId) ;
        Contact  contact = optContact.get() ;

        this.contactRepository.delete(contact);
        session.setAttribute("message", new Message("Contact has been deleted !!","alert-success")) ;
        User user = (User) model.getAttribute("user") ;

        return "redirect:/user/view-contact/0" ;
    }

    @GetMapping("/update-contact/{cId}")
    public String updateContact(@PathVariable("cId") Integer cId , Model model, HttpSession session){
    
        Optional<Contact> optContact = this.contactRepository.findById(cId) ;
        Contact  contact = optContact.get() ;

        model.addAttribute("contact", contact) ;

        return "update_contact" ;
    }

        @PostMapping("edit-contact")
    public String editContact(@ModelAttribute("contact") Contact contact,@RequestParam("profileImage") MultipartFile file , Model model, HttpSession session) throws IOException{
       
        try{
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

            this.contactRepository.save(contact) ;
            session.setAttribute("message",new Message("Contact Updated succesfully !!", "alert-success")) ;
        }catch(Exception e)
        {
            session.setAttribute("message",new Message("Error while updating contact !!", "alert-danger")) ;
            e.printStackTrace();

        }
        return "redirect:/user/view-contact/0" ;
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
     
        return "user_profile" ;
    }

        @GetMapping("/settings")
    public String getSetting(Model model){
     
        return "user_settings" ;
    }

    @PostMapping("/change-pass")
    public String changePass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass,@RequestParam("confirmPass") String confirmPass, Principal principal, Model model, HttpSession session )
    {
        User user = (User) model.getAttribute("user") ;
        String existPass = user.getPassword() ;
        if(!confirmPass.equals(newPass)){
             session.setAttribute("message",new Message("New password is not same of confirm password !!", "alert-danger"));
              return "user_settings" ;
        } 
        if(!this.bCryptPasswordEncoder.matches(oldPass,existPass)) {
             session.setAttribute("message",new Message("Old password does not matches !!", "alert-danger"));
             return "user_settings" ;
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(newPass));
        this.userRepository.save(user) ;
        session.setAttribute("message",new Message("Password changed succesfully !!", "alert-success")) ;
        return "redirect:/user/index" ;
    }

}
