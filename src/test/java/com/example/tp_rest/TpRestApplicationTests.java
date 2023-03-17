package com.example.tp_rest;
import com.example.tp_rest.controllers.BookRestControllerAPI;
import com.example.tp_rest.entities.Book;
import com.example.tp_rest.exception.BookNotFoundException;
import com.example.tp_rest.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@AutoConfigureMockMvc
@SpringBootTest
class TpRestApplicationTests {

    private MockMvc mockMvc;

    @InjectMocks
    BookRestControllerAPI bookRestControllerAPI;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookRestControllerAPI).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book(1L,"The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald");
        Book book2 = new Book(2L,"To Kill a Mockingbird","978-0-06-112008-4","Harper Lee");
        List<Book> userList = Arrays.asList(book1, book2);

        Mockito.when(bookService.getAllBooks()).thenReturn(userList);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Great Gatsby"))
                .andExpect(jsonPath("$[0].isbn").value("978-3-16-148410-0"))
                .andExpect(jsonPath("$[1].name").value("To Kill a Mockingbird"))
                .andExpect(jsonPath("$[1].isbn").value("978-0-06-112008-4"));
    }


    @Test
    public void testGetBook() throws Exception , BookNotFoundException {
        Book book = new Book(1L,"The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald");

        Mockito.when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("The Great Gatsby"))
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"));
    }

    @Test
    public void testSaveBook() throws Exception {
        Book book = new Book(1L,"The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald");

        Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("The Great Gatsby"))
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(1L,"The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald");

        Mockito.when(bookService.updateBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("The Great Gatsby"))
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookById_thenThrowException() throws Exception, BookNotFoundException {

        Mockito.when(bookService.getBookById(3L)).thenThrow(new BookNotFoundException("this book is not exist"));

        mockMvc.perform(get("/books/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("this book is not exist")));
    }
}
