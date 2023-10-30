package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.model.Student;

// Class for CRUD operation in database
public interface StudentRepository extends CrudRepository<Student, Integer>  {

}
