package com.api.springbootrestapi.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    private String name ;

    @OneToOne(cascade = CascadeType.ALL)
    private Author author ;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", author=" + author + "]";
    }

    public Book() {
    }

    public Book(int id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }


}
