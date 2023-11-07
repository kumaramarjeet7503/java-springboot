package com.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.model.Student;

// Class for CRUD operation in database
public interface StudentRepository extends CrudRepository<Student, Integer>  {
	
	public List<Student>  findByNameIgnoreCase(String Name)  ;
	public List<Student>  findByNameAndCity(String Name, String City)  ;
	public List<Student> findByCourseLike(String Course) ;
	public List<Student> findByCourse(String Course) ;
	
//	 Executing JPQL with spring JPA
	@Query("select s from Student where s.Name =:n")
	public List<Student> getAllStudent(@Param("n") String Name);
	
//	 For Native Query
	@Query(value="select * from Student",nativeQuery = true)
	public List<Student> getAllStudent();
}
