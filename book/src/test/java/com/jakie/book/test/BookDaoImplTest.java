//package com.jakie.book.test;
//
//import com.jakie.book.dao.BookDao;
//import com.jakie.book.dao.impl.BookDaoImpl;
//import com.jakie.book.pojo.Book;
//import com.jakie.book.pojo.Page;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//class BookDaoImplTest {
//
//    private BookDao bookDao  = new BookDaoImpl();
//
//    @Test
//    void queryBookById() {
//        System.out.println(bookDao.queryBookById(1));
//    }
//
//    @Test
//    void addBook() {
//        System.out.println(bookDao.addBook(new Book(null,"xxx",new BigDecimal(99.55),"xx",1000,100,null)));
//    }
//
//    @Test
//    void deleteBookById() {
//        System.out.println(bookDao.deleteBookById(6));
//    }
//
//    @Test
//    void updateBook() {
//        System.out.println(bookDao.updateBook(new Book(6,"篮球入门培训",new BigDecimal(1000),"库里",100,0,null)));
//    }
//
//    @Test
//    void queryBookList() {
//        List<Book> books = bookDao.queryBookList();
//        for(Book idx:books)
//            System.out.println(idx);
//    }
//
//
//    @Test
//    void queryForPageTotalCount() {
//        Integer integer = bookDao.queryForPageTotalCount();
//        System.out.println(integer);
//
//    }
//
//    @Test
//    void queryForPageItems() {
//        List<Book> books = bookDao.queryForPageItems(1, Page.PAGE_SIZE);
//
//        for(Book idx:books){
//            System.out.println(idx);
//        }
//    }
//
//    @Test
//    public void queryBookByPrice() {
//        List<Book> books = bookDao.queryBookByPrice(100, 200,0,Page.PAGE_SIZE);
//        for(Book book:books)
//            System.out.println(book);
//    }
//
//    @Test
//    void queryBookMaxPrice(){
//        Integer  max= bookDao.queryBookMaxPrice();
//        System.out.println(max);
//    }
//
//    @Test
//    void queryBookMinPrice(){
//        Integer min = bookDao.queryBookMinPrice();
//        System.out.println(min);
//    }
//}