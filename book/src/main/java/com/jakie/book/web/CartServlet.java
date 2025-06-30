package com.jakie.book.web;

import com.jakie.book.pojo.Book;
import com.jakie.book.pojo.Cart;
import com.jakie.book.pojo.CartItem;
import com.jakie.book.service.BookService;
import com.jakie.book.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //获取bookid
        String bookId = req.getParameter("book_id");
        int id = Integer.parseInt(bookId);

        //通过bookid查找 book相关信息
        Book book = bookService.queryBookById(id);

        //查看session会话中是否存在cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        req.getSession().setAttribute("lastAddBook",book.getName());
        cart.addItem(new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice()));

        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteItem 方法被调用");
        String bookId = request.getParameter("book_id");
        int id = Integer.parseInt(bookId);

        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.deleteItem(id);

        response.sendRedirect(request.getHeader("Referer"));
    }


    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");

        cart.clear();

        System.out.println("clear方法中"+request.getContextPath());
        response.sendRedirect(request.getHeader("Referer"));
    }
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CartServlet中 updateCount方法");
        String bookId = request.getParameter("book_id");
        String book_count = request.getParameter("newCount");

        System.out.println("bookId"+bookId+"  book_cound "+book_count);
        int id = Integer.parseInt(bookId);
        int count = Integer.parseInt(book_count);


        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.updateCount(id,count);

        response.sendRedirect(request.getHeader("Referer"));
    }

}
