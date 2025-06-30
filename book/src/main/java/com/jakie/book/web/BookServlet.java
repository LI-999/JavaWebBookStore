package com.jakie.book.web;

import com.jakie.book.dao.impl.BaseDao;
import com.jakie.book.pojo.Book;
import com.jakie.book.pojo.Page;
import com.jakie.book.service.BookService;
import com.jakie.book.service.impl.BookServiceImpl;
import com.jakie.book.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    public void delete(HttpServletRequest req,HttpServletResponse resp){
        Integer id = Integer.valueOf(req.getParameter("id"));
        bookService.deleteBookById(id);


        try {
            resp.sendRedirect(req.getContextPath()+"${requestScope.page.url}?action=page&pageNo="+req.getParameter("pageNo"));
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /***
     * @Author jakie
     * @Description //TODO getBook方法用于update修改使用  通过req请求参数的id值获取图书信息
     * @Date 9:52 2025/6/19
     * @Param [req, resp]
     * @return void
     **/
    public void getBook(HttpServletRequest req,HttpServletResponse resp){
        Integer id = Integer.valueOf(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        req.setAttribute("update_book",book);
        req.setAttribute("action","update");
        try {
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(HttpServletRequest req,HttpServletResponse resp){
        Map<String, String[]> parameterMap = req.getParameterMap();
        for(Map.Entry<String,String[]>idx:parameterMap.entrySet()){
            System.out.println(idx.getKey()+" "+Arrays.asList(idx.getValue()));
        }
        Book book = WebUtils.copyParamToBean(parameterMap, new Book());
        bookService.updateBook(book);
        try {
            resp.sendRedirect(req.getContextPath()+"${requestScope.page.url}?action=page&pageNo="+req.getParameter("pageNo"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void add(HttpServletRequest req,HttpServletResponse resp){
        //查看请求参数列表
//        Map<String, String[]> parameterMap = req.getParameterMap();
//        System.out.println("parameterMap "+parameterMap.size());
//        for(Map.Entry<String,String[]> idx:parameterMap.entrySet()){
//            System.out.println(idx.getKey()+" "+ Arrays.asList(idx.getValue()));
//        }


        System.out.println("总页码 "+req.getParameter("pageNo"));
        //将请求域中的参数封装到Bean中
        Book book = WebUtils.copyParamToBean( req.getParameterMap(),new Book());

        System.out.println(book);
        bookService.addBook(book);
        String pageNo = req.getParameter("pageNo");


        try {
            resp.sendRedirect(req.getContextPath()+"${requestScope.page.url}?action=page&pageNo="+(Integer.valueOf(req.getParameter("pageNo"))+1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void list(HttpServletRequest req,HttpServletResponse resp){
        List<Book> books = bookService.queryBookList();
        req.setAttribute("book",books);
        try {
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public void page(HttpServletRequest req,HttpServletResponse resp){
        String pageNo_str = req.getParameter("pageNo");
        String pageSize_str = req.getParameter("pageSize");
        //如果传递的参数中pageNo和pageSize空 则pageNo为1 默认显示第一页  pageSize 等于 page类中 PAGE_SIZE 默认值
        Integer pageNo = Integer.valueOf(pageNo_str==null?"1":pageNo_str);
        Integer pageSize = Integer.valueOf(pageSize_str==null?Page.PAGE_SIZE+"":pageSize_str);
        Page page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet");
        req.setAttribute("page",page);



//        System.out.println("page       "+page.getPageNo());

        try {
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
