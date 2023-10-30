package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>  {

}
