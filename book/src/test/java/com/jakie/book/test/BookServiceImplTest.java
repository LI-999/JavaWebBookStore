package com.jakie.book.test;

import com.jakie.book.pojo.Book;
import com.jakie.book.service.BookService;
import com.jakie.book.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    void addBook() {
        bookService.addBook(new Book(null,"代码整洁之道", new BigDecimal(69.99), "Robert C. Martin", 1200, 250, ""));
    }

    @Test
    void deleteBookById() {
        bookService.deleteBookById(7);
    }

    @Test
    void updateBook() {
        bookService.updateBook(new Book(7,"Head First Design Patterns", new BigDecimal(54.99), "Eric Freeman", 850, 180, ""));
    }

    @Test
    void queryBookById() {
        System.out.println(bookService.queryBookById(7));
    }

    @Test
    void queryBookList() {
        List<Book> books = bookService.queryBookList();
        for (Book book : books) {
            System.out.println(book);

        }

    }
}