package com.example.tp_rest.services;

import com.example.tp_rest.entities.Book;
import com.example.tp_rest.exception.BookNotFoundException;

import java.util.List;


public interface BookService {
    public Book createBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
    public void deleteBookById(Long idBook);
    public Book getBookById(Long idBook) throws BookNotFoundException;
    public List<Book> getAllBooks();
}
