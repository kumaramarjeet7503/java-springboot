package com.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Hibernate for making it as entity to communicate with database
@Entity
@Table(name="student")
public class Student {

	@jakarta.persistence.Id
	private int Id ;
	
	@Column(name="Course")
	private String Course ;
	@Column(name="City")
	private String City ;
	@Column(name="Name")
	private String Name ;
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCourse() {
		return Course;
	}

	public void setCourse(String course) {
		Course = course;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Student(int id) {
		super();
		Id = id;
	}

	public Student(String course, String city, String name) {
		super();
		Course = course;
		City = city;
		Name = name;
	}

	@Override
	public String toString() {
		return "Student [Id=" + Id + ", Course=" + Course + ", Duration= , City=" + City + ", Name="
				+ Name + "]";
	}

	public Student() {
		
	}

	public Student(int id, String course, double duration, String city, String name) {
		super();
		Id = id;
		Course = course;
		City = city;
		Name = name;
	}



}
