package com.api.springbootrestapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.api.springbootrestapi.entities.Book;
import com.api.springbootrestapi.service.BookService;
import java.util.* ;

//  Rest controller so that response will not go in html page
@RestController
public class BookController {
    
    //  Autowiring to create an object by spring
    @Autowired
    private BookService bookService ;

    @RequestMapping(value="/books",method = RequestMethod.GET)
    //  Send the response to the client as it is returning like no html will be returned
    @ResponseBody
    public String firstBook()
    {
        return  "This is our first rest API project" ;
    }

    //  Get single book
    //  Get Mapping is a combination of request mapping with type get
    // {id} is the id passed by the user
    @GetMapping("/get-books/{id}")
    public Book getBook(@PathVariable("id") int id)
    {
        Book book = bookService.getSingleBook(id) ;
       
        return book ;
    }

    @GetMapping("/get-single-book")
    public Book getSingleBook()
    {
        Book book = new Book(1,"Death","Shankara") ;
        return book ;
    }

    @GetMapping("/get-all-book")
    public List<Book> getAllBooks()
    {
        List<Book> books = bookService.getAllBook() ;
        return books ;
    }

    //  For handling post data from postman
    @PostMapping("/create-book")
    public List<Book> createBook(@RequestBody Book book)
    {
        List<Book> books = bookService.createBook(book) ;
        return books ;
    }

    @DeleteMapping("/delete-book/{id}")
    public void deleteBook(@PathVariable("id") int id)
    {
        bookService.deleteBook(id);
    }

    @PutMapping("/update-book/{id}")
    public List<Book> updateBook( @RequestBody Book book , @PathVariable("id") int id )
    {
        List<Book> books = bookService.updateBook(id, book) ;
        return books ;
    }
}
