package com.jakie.book.service.impl;

import com.jakie.book.dao.BookDao;
import com.jakie.book.dao.impl.BookDaoImpl;
import com.jakie.book.pojo.Book;
import com.jakie.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();
    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBookList() {
        return bookDao.queryBookList();
    }
}
