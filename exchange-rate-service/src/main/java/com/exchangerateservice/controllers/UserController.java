package com.exchangerateservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerateservice.entities.User;
import com.exchangerateservice.pojo.AuthRequest;
import com.exchangerateservice.service.JwtService;
import com.exchangerateservice.service.UserService;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/auth")
public class UserController {
    

    @Autowired
    private UserService userService ;

    @Autowired 
    private AuthenticationManager authenticationManager ; 

    @Autowired
    private JwtService jwtService ;

    Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/dashboard")
    public String getDashboard() {
        logger.info("this is the testing log");
        logger.debug("this is the debug logger");
        return new String("Welcome to authentication dashboard");
    }

    @PostMapping("/create-user")
    public String createUses(@RequestBody User user) {
        String response =  this.userService.addUser(user) ;
        return response;
    }
    
        @GetMapping("/user/profile")
    @PreAuthorize("hasAuthority('user')")
    public String getUserProfile() {
        return new String("You are into the user profile");
    }

    @GetMapping("/admin/profile")
    @PreAuthorize("hasAuthority('admin')")
    public String getAdminProfile() {
        return new String("You are into the admin profile");
    }

    @PostMapping("/generate-token")
    public String generateToken(@RequestBody AuthRequest authRequest ) {
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))  ;
       if(authentication.isAuthenticated())
       {
            return jwtService.generateToken(authRequest.getUsername()) ;
       }else{
            throw new UsernameNotFoundException("invalid user request") ;
       }
    }
    
    

}
