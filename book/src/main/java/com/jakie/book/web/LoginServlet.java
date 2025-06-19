package com.jakie.book.web;

import com.jakie.book.pojo.User;
import com.jakie.book.service.UserService;
import com.jakie.book.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Deprecated
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(new User(null, username, password, null));
        if(user!=null){
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            System.out.println("用户名不存在");
            req.setAttribute("Msg","用户名不存在");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }

    }
}
