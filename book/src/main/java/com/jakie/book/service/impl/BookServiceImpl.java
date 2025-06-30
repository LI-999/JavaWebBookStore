package com.jakie.book.service.impl;

import com.jakie.book.dao.BookDao;
import com.jakie.book.dao.impl.BookDaoImpl;
import com.jakie.book.pojo.Book;
import com.jakie.book.pojo.Page;
import com.jakie.book.service.BookService;

import java.sql.SQLException;
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
    public List<Book> queryBookList(){
        return bookDao.queryBookList();
    }

    @Override
    public Page page(Integer pageNo, Integer pageSize) {
        Page page = new Page();

        //总记录数
        Integer total = bookDao.queryForPageTotalCount();

        page.setUrl("client/bookServlet?action=page");

        //设置总记录数
        page.setPageTotalCount(total);

        //每页显示数量 被写死在page类中

        //总页码 等于 total / pageSIZE + 1
        total = (total-1)/pageSize+1;
        page.setPageTotal(total);

        //确保设置的pageNo 在[1-总页码之间]
        pageNo = Math.min(Math.max(pageNo,1),page.getPageTotal());

        System.out.println("pageNo "+pageNo);

        //当前页页码
        page.setPageNo(pageNo);

        //当前页数据
        page.setItems(bookDao.queryForPageItems(pageNo,pageSize));

        return page;
    }

    @Override
    public Page queryBookByPrice(Integer min, Integer max,Integer pageNo,Integer pageSize) {
        Page page = new Page();

        //满足该价格区间的总记录数
        int total = bookDao.queryForPageTotalCountByPrice(min,max);

//        page.setUrl("client/bookServlet?action=queryPrice");

        page.setPageTotal((total-1)/ Page.PAGE_SIZE+1);

        page.setPageTotalCount(total);

        page.setPageNo(pageNo);

        List<Book> books = bookDao.queryBookByPrice(min, max,pageNo,pageSize);

        page.setItems(books);

        //注释这几行 功能对价格的区间进行判断  但觉得没必要实现 区间有误会不显示任何结果

//        Integer minPrice = bookDao.queryBookMinPrice();
//        Integer maxPrice = bookDao.queryBookMaxPrice();

//        min = Math.min(Math.max(min,minPrice),maxPrice);
//        max = Math.max(Math.min(max,maxPrice),minPrice);

        System.out.println("Service层 queryBookByPrice "+min+" "+max+" "+pageNo);

        return page;


    }
}
