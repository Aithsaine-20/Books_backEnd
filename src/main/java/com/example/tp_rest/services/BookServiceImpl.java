package com.example.tp_rest.services;

import com.example.tp_rest.entities.Book;
import com.example.tp_rest.exception.BookNotFoundException;
import com.example.tp_rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void deleteBookById(Long idBook) {
        bookRepository.deleteById(idBook);
    }

    @Override
    public Book getBookById(Long idBook) throws BookNotFoundException {
        return bookRepository.findById(idBook).orElseThrow(()->new BookNotFoundException("this book is not exist"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
