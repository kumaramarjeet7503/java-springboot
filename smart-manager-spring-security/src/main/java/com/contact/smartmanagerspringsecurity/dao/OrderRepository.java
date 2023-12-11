package com.contact.smartmanagerspringsecurity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.contact.smartmanagerspringsecurity.entitity.RazorOrder;
import java.util.List;


public interface OrderRepository extends JpaRepository <RazorOrder,Integer> {
    public RazorOrder findByOrderId(String orderId);
}
