package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Person;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repo.PersonRepo;

import jakarta.transaction.Transactional;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepo personRepo ;

    @Transactional
    public Person createPerson(Person person)
    {
        Person createdPerson = this.personRepo.save(person) ;
        return createdPerson ;
    }

    public Person getPerson(int personId)
    {
        return this.personRepo.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Please enter a correct person Id .")) ;
    }

    public Person updatePerson(int personId, String name)
    {
        Person updatedPerson = this.personRepo.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Please enter a correct person Id .")) ;
        updatedPerson.setName(name);
        this.personRepo.save(updatedPerson) ;
        return updatedPerson ;
    }

    public List<Person> getAllPerson()
    {
        List<Person> people = this.personRepo.findAll() ;
        return people ;
    }

}
