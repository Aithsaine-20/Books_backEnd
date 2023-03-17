package com.example.tp_rest.controllers;

import com.example.tp_rest.entities.Book;
import com.example.tp_rest.exception.BookNotFoundException;
import com.example.tp_rest.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3001/")
@RequestMapping("books")
public class BookRestControllerAPI {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping(path = "/{idBook}")
    public Book getBookById(@PathVariable Long idBook) throws BookNotFoundException {
        return bookService.getBookById(idBook);
    }
    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return bookService.createBook(book);
    }
    @PutMapping
    public Book updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping(path = "/{idBook}")
    public void deleteBook(@PathVariable Long idBook){
        bookService.deleteBookById(idBook);
    }





    @org.springframework.web.bind.annotation.ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> BookNotFound(BookNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
