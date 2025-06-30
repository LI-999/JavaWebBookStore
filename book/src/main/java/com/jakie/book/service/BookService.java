package com.jakie.book.service;

import com.jakie.book.pojo.Book;
import com.jakie.book.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    public int addBook(Book book);

    public int deleteBookById(int id);

    public int updateBook(Book book);
    public Book queryBookById(int id);

    public List<Book> queryBookList();

    public Page page(Integer pageNo,Integer pageSIZE);


    public Page queryBookByPrice(Integer min,Integer max,Integer pageNo,Integer pageSize);


}
