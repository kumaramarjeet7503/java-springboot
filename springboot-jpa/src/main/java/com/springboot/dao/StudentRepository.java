package com.springboot.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.model.Student;

// Class for CRUD operation in database
public interface StudentRepository extends CrudRepository<Student, Integer>  {
	
	public List<Student>  findByNameIgnoreCase(String Name)  ;
	public List<Student>  findByNameAndCity(String Name, String City)  ;
	public List<Student> findByCourseLike(String Course) ;
	public List<Student> findByCourse(String Course) ;
}
