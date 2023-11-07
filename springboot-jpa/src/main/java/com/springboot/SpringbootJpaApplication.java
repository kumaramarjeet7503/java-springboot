package com.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.dao.StudentRepository;
import com.springboot.model.Student;

@SpringBootApplication
public class SpringbootJpaApplication {

	public static void main(String[] args) {

//		Getting application context
	  ApplicationContext  context =	SpringApplication.run(SpringbootJpaApplication.class, args);
	  StudentRepository dao = context.getBean(StudentRepository.class) ;

//	  Create new student with getting bean via spring boot
	  Student student = new Student("jaba", "delhi", "Ravan") ;

//	  Saving for creating new object
	  dao.save(student) ;

//	  Update for existing student
	  Optional<Student> optional = dao.findById(6) ;
	  Student student = optional.get();
	  student.setName("Shivalik");
	  dao.save(student) ;

//	  Find all existing student
	  Iterable<Student> students = dao.findAll() ;
//
	  students.forEach(student->System.out.println(student));

//	    Derived Query for getting data
	  List<Student> studentsByName =  dao.findByNameIgnoreCase("Amarjeet") ;
	  List<Student> studentsCourse =  dao.findByCourse("springboot");
	  studentsByName.forEach(student->System.out.println(student)) ;
	  List<Student> studentsNameAndCity =  dao.findByNameAndCity( "surya","Lonalva") ;
	  List<Student> students = dao.getAllStudent("Amarjeet") ;
	  students.forEach(student->{ System.out.println(student); });

	}

}
