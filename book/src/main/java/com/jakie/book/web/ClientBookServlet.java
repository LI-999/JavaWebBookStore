package com.jakie.book.web;

import com.jakie.book.pojo.Book;
import com.jakie.book.pojo.Page;
import com.jakie.book.service.impl.BookServiceImpl;
import com.jakie.book.service.BookService;
import com.jakie.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientBookServlet extends BaseServlet {

    public BookService bookService = new BookServiceImpl();

    public void page(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String id = req.getParameter("pageNo");
        Integer pageNo = 1;
        if (id != null)
            pageNo = Integer.valueOf(id);

        req.setAttribute("page",bookService.page(pageNo,Page.PAGE_SIZE));

        try {
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public void pageByPrice(HttpServletRequest req,HttpServletResponse resp){
//        System.out.println("Client page被调用");
//        String id = req.getParameter("pageNo");
//        System.out.println("client page " + id);
//        Integer pageNo = 1;
//        if (id != null)
//            pageNo = Integer.valueOf(id);
//
//        List<Book> books = (List<Book>)req.getAttribute("books");
//
//        Page page = new Page();
//
//        page.setItems(books);
//
////        page.setPageTotal();
//
//        page.setPageNo(1);
//
//        page.setUrl("client/bookServlet");
//
//        page.setPageTotalCount(books.size());
//
//        req.setAttribute("page", page);
//
//        for(Book idx:books){
//            System.out.println(idx);
//        }
//
//        System.out.println(page);
//
//        try {
//            req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void queryPrice(HttpServletRequest req, HttpServletResponse resp) throws SQLException {

        System.out.println("queryPrice 被调用");
        //获取请求参数
        String max_str = req.getParameter("max");
        String min_str = req.getParameter("min");
        String pageNo_str = req.getParameter("pageNo");
        Integer max = Integer.valueOf(max_str);
        Integer min = Integer.valueOf(min_str);
        System.out.println(min+" "+max);
        //max min要做判断防止 min 大于 max ？
        Integer pageNo = 1;
        if(pageNo_str!=null)
            pageNo = Integer.valueOf(pageNo_str);

        //调用service层方法
        Page page = bookService.queryBookByPrice(min, max,pageNo,Page.PAGE_SIZE);
        page.setUrl("client/bookServlet?action=queryPrice&min="+min+"&max="+max);
        req.setAttribute("page", page);

        try {
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
