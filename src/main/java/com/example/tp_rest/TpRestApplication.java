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
/*
Book book1 = new Book();
book1.setName("The Great Gatsby");
book1.setISBN("978-3-16-148410-0");
book1.setAuthor("F. Scott Fitzgerald");

Book book2 = new Book();
book2.setName("To Kill a Mockingbird");
book2.setISBN("978-0-06-112008-4");
book2.setAuthor("Harper Lee");

* */