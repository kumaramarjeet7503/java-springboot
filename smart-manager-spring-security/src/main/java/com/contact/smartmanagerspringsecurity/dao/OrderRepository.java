package com.contact.smartmanagerspringsecurity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.contact.smartmanagerspringsecurity.entitity.RazorOrder;

public interface OrderRepository extends JpaRepository <RazorOrder,Integer> {
    
}
