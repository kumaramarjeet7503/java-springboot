package com.api.springbootrestapi.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.springbootrestapi.dao.BookRepository;
import com.api.springbootrestapi.entities.Book;

@Component
public class BookService {
    // private static List<Book> books = new ArrayList() ;

    // static{
    //     books.add(new Book(1,"Java Fundamentals","Manish"));
    //     books.add(new Book(2,"C++ Fundamentals","Sukesh")) ;
    //     books.add(new Book(3,"PHP Fundamentals","Arvina")) ;
    // }
    
    @Autowired
    private BookRepository bookRepository ;

    public Book getSingleBook(int id)
    {
        //  Compare object id in a list and get single book object
        Book book = null ;
        try {
            //  book = books.stream().filter(e->e.getId() == id).findFirst().get() ;
            book = bookRepository.findById(id) ;
        } catch (Exception e) {
            e.printStackTrace();
        } 
       
        return book ;
    }

    public List<Book> getAllBook()
    {
         List<Book> books = (List<Book>) bookRepository.findAll() ;
        return books ;
    }

    //  Add new book via post request
    public List<Book> createBook(Book book)
    {
       bookRepository.save(book) ;
        return (List<Book>) bookRepository.findAll()  ;
    }

    //  Delete existing book as per the request
    public void deleteBook(int id)
    {
        // books = books.stream().filter(book -> book.getId() != id).collect(Collectors.toList()) ;
        bookRepository.deleteById(id);;
    }

    //  Update existing book records via API 
    public List<Book> updateBook(int id, Book updatedBook)
    {
        // books = books.stream() ;
    //    books.stream().map(b -> {
    //         if(b.getId() == id)
    //         {
    //             b.setAuthor(updatedBook.getAuthor()) ;
    //             b.setName(updatedBook.getName()); 
    //         }
    //         return b ;
    //     }).collect(Collectors.toList()) ;
        updatedBook.setId(id);
       bookRepository.save(updatedBook) ;
        return (List<Book>) bookRepository.findAll()  ;
    }
}
