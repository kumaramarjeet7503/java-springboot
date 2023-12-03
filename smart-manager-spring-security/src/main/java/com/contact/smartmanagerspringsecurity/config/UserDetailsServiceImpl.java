package com.contact.smartmanagerspringsecurity.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.contact.smartmanagerspringsecurity.dao.UserRepository;
import com.contact.smartmanagerspringsecurity.entitity.User;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.getUserByUserName(username);
         if(user == null) throw new UsernameNotFoundException("user not found") ;

         CustomerUserDetails customerUserDetails = new CustomerUserDetails(user) ;
         return customerUserDetails ;
    }
    
}
