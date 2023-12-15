package com.springbootjunit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbootjunit.entity.Student;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository ;

    @Test
    void findAll(){
          Boolean isExist = this.studentRepository.existsById(3) ;
          assertEquals(isExist,true);
    }
}
