package com.exchangerateservice.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exchangerateservice.entities.User;
import com.exchangerateservice.helper.UserInfoDetails;
import com.exchangerateservice.repositories.UserRepo;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo repo ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails = repo.findByName(username) ;
         return userDetails.map(UserInfoDetails::new).orElseThrow(()-> new UsernameNotFoundException(username) ) ;
    }

    public String addUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword())) ;
        repo.save(user) ;
        return "User added successfully" ;
    }
    
}
