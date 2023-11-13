package com.api.springbootrestapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Book> getBook(@PathVariable("id") int id)
    {
        Book book = bookService.getSingleBook(id) ;
        if(book == null)
        {
            //  For sending response with status not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        }
        //  sending data via response entity
        return ResponseEntity.of(Optional.of(book)) ;
    }

    // @GetMapping("/get-single-book")
    // public Book getSingleBook()
    // {
    //     Book book = new Book(1,"Death","Shankara") ;
    //     return book ;
    // }

    @GetMapping("/get-all-book")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> books = bookService.getAllBook() ;
        if(books.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        }
        return ResponseEntity.of(Optional.of(books)) ;
    }

    //  For handling post data from postman
    @PostMapping("/create-book")
    public ResponseEntity<List<Book>> createBook(@RequestBody Book book)
    {
        try {
             List<Book> books = bookService.createBook(book) ;
             return ResponseEntity.of(Optional.of(books)) ;
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id)
    {
        try {
            bookService.deleteBook(id);
           
        } catch (Exception e) {
            // TODO: handle exception
        }
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity<List<Book>> updateBook( @RequestBody Book book , @PathVariable("id") int id )
    {
        try {
          List<Book> books = bookService.updateBook(id, book) ;
           return ResponseEntity.of(Optional.of(books)) ;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
    }
}
