package com.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entities.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    
}
