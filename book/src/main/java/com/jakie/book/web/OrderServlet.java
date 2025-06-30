package com.jakie.book.web;

import com.jakie.book.pojo.Cart;
import com.jakie.book.pojo.User;
import com.jakie.book.service.OrderService;
import com.jakie.book.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
//        System.out.println(cart);
//        Cookie[] cookies = req.getCookies();
//        for(Cookie idx:cookies)
//            System.out.println(idx.getName()+" "+idx.getValue()+" ");

        User user = (User)req.getSession().getAttribute("user");
        if(user==null){
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
            //不加return会执行if语句之后的语句
            return;
        }

        //返回生成的订单编号
        String orderId = orderService.createOrder(cart, user.getId());
        System.out.println("生成的订单编号 "+orderId);
        req.getSession().setAttribute("orderId",orderId);

        //防止表单重复提交 改用请求重定向
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
