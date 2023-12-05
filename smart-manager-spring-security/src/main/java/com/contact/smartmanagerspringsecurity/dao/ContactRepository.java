package com.contact.smartmanagerspringsecurity.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contact.smartmanagerspringsecurity.entitity.Contact;
import com.contact.smartmanagerspringsecurity.entitity.User;
import java.util.List;

public interface ContactRepository extends JpaRepository < Contact , Integer> {
    @Query("select u from Contact u where u.user.id = :userId")
    public  Page<Contact> getContactsByUserId(@Param("userId") Integer userId,Pageable pageable ) ;
}
