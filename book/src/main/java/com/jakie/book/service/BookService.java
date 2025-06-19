package com.jakie.book.service;

import com.jakie.book.pojo.Book;

import java.util.List;

public interface BookService {
    public int addBook(Book book);

    public int deleteBookById(int id);

    public int updateBook(Book book);
    public Book queryBookById(int id);

    public List<Book> queryBookList();
}
