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
        Book book = null ;
        try {
             book = books.stream().filter(e->e.getId() == id).findFirst().get() ;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return book ;
    }

    public List<Book> getAllBook()
    {
        return books ;
    }

    //  Add new book via post request
    public List<Book> createBook(Book book)
    {
        books.add(book) ;
        return books ;
    }

    //  Delete existing book as per the request
    public void deleteBook(int id)
    {
        books = books.stream().filter(book -> book.getId() != id).collect(Collectors.toList()) ;
    }

    //  Update existing book records via API 
    public List<Book> updateBook(int id, Book updatedBook)
    {
        // books = books.stream() ;
       books.stream().map(b -> {
            if(b.getId() == id)
            {
                b.setAuthor(updatedBook.getAuthor()) ;
                b.setName(updatedBook.getName()); 
            }
            return b ;
        }).collect(Collectors.toList()) ;
     
        return books ;
    }
}
