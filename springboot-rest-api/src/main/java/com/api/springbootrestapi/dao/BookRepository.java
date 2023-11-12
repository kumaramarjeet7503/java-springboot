package com.api.springbootrestapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.springbootrestapi.entities.Book;
import java.util.List;


public interface BookRepository extends CrudRepository<Book, Integer> {
    public Book  findById(int id) ;
}
