package com.contact.smartmanagerspringsecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.contact.smartmanagerspringsecurity.entitity.User;

public interface UserRepository extends JpaRepository < User , Integer> {
    
}
