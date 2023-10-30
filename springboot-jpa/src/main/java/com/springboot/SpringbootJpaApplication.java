package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.dao.StudentRepository;
import com.springboot.model.Student;

@SpringBootApplication
public class SpringbootJpaApplication {

	public static void main(String[] args) {
	  ApplicationContext  context =	SpringApplication.run(SpringbootJpaApplication.class, args);
	  StudentRepository dao = context.getBean(StudentRepository.class) ;
	  Student student = new Student("jaba", "delhi", "Ravan") ;
	  dao.save(student) ;
	}

}
