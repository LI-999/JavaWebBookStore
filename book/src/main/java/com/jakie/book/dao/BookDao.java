package com.jakie.book.dao;
import com.jakie.book.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(int id);

    public int updateBook(Book book);
    public Book queryBookById(int id);

    public List<Book> queryBookList();

    //求总记录数
    public Integer queryForPageTotalCount();

    //求价格区间的记录数
    public Integer queryForPageTotalCountByPrice(Integer min,Integer max);

    //求当前页数据
    public List<Book> queryForPageItems(Integer begin,Integer pageSize);

    public List<Book> queryBookByPrice(Integer min, Integer max,Integer pageNo,Integer pageSize);

    public Integer queryBookMinPrice();

    public Integer queryBookMaxPrice();

}
