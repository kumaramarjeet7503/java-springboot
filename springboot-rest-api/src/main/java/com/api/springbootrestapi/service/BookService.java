package com.api.springbootrestapi.service;

import java.util.*;
import java.util.stream.Collectors;  
import org.springframework.stereotype.Component;

import com.api.springbootrestapi.entities.Book;

@Component
public class BookService {
    private static List<Book> books = new ArrayList() ;

    static{
        books.add(new Book(1,"Java Fundamentals","Manish"));
        books.add(new Book(2,"C++ Fundamentals","Sukesh")) ;
        books.add(new Book(3,"PHP Fundamentals","Arvina")) ;
    }

    public Book getSingleBook(int id)
    {
        //  Compare object id in a list and get single book object
        Book book = books.stream().filter(e->e.getId() == id).findFirst().get() ;
        return book ;
    }

    public List<Book> getAllBook()
    {
        return books ;
    }

    public List<Book> createBook(Book book)
    {
        books.add(book) ;
        return books ;
    }
}
