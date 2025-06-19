package com.jakie.book.dao.impl;

import com.jakie.book.dao.BookDao;
import com.jakie.book.pojo.Book;

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
    public int deleteBookById(int id) {
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
}
