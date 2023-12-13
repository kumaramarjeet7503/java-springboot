package com.springbootgeneratedocument.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootgeneratedocument.entitiy.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
