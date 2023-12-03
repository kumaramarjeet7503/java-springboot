package com.contact.smartmanagerspringsecurity.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.contact.smartmanagerspringsecurity.entitity.User;

public class CustomerUserDetails implements UserDetails {

    private User user ;
    

    public CustomerUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       SimpleGrantedAuthority authrority = new SimpleGrantedAuthority(user.getRole()) ;
        return List.of(authrority) ;
    }

    @Override
    public String getPassword() {
         return user.getPassword() ;
    }

    @Override
    public String getUsername() {
        return user.getEmail() ; 
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true ;
        }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled() ;
    }

}
