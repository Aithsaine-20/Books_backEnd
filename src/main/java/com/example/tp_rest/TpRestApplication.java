package com.example.tp_rest;

import com.example.tp_rest.entities.Book;
import com.example.tp_rest.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpRestApplication implements CommandLineRunner {
    @Autowired
    BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(TpRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookService.createBook(new Book(null, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald"));
        bookService.createBook(new Book(null, "To Kill a Mockingbird","978-0-06-112008-4","Harper Lee"));


    }
}
