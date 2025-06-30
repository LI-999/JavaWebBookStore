package com.jakie.book.dao.impl;

import com.jakie.book.dao.BookDao;
import com.jakie.book.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public Book queryBookById(int id) {
        String sql  = "select * from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name, price, author, sales, stock, img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),null);
    }

    @Override
    public int deleteBookById(int id)  {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id =?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public List<Book> queryBookList() {
        String sql = "select * from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        return queryForSingleValue(sql).intValue();
    }

    @Override
    public Integer queryForPageTotalCountByPrice(Integer min,Integer max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return queryForSingleValue(sql,min,max).intValue();
    }

    @Override
    public List<Book> queryForPageItems(Integer begin,Integer pageSize) {
        String sql = "select * from t_book limit ?,?";
        System.out.println((begin-1)*pageSize);
        return queryForList(Book.class,sql,(begin-1)*pageSize,pageSize);
    }

    @Override
    public List<Book> queryBookByPrice(Integer min, Integer max,Integer pageNo,Integer pageSize) {
        String sql = "select * from t_book where price between ? and ? limit ?,?";
        return queryForList(Book.class,sql,min,max,(pageNo-1)*pageSize,pageSize);
    }

    @Override
    public Integer queryBookMinPrice() {
        String sql = "select min(price) from t_book";
        return queryForSingleValue(sql).intValue();
    }

    @Override
    public Integer queryBookMaxPrice() {
        String sql = "select max(price) from t_book";
        return queryForSingleValue(sql).intValue();
    }
}
