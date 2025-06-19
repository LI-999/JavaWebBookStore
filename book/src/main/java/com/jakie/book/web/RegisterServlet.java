package com.jakie.book.web;

import com.jakie.book.pojo.User;
import com.jakie.book.service.UserService;
import com.jakie.book.service.impl.UserServiceImpl;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@Deprecated
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        System.out.println(username + " " + password + " " + email + " " + code);
        //验证码写成静态的
        if ("abcde".equals(code)) {
            if (!userService.existsUsername(username)) {
                System.out.println("注册成功");
                userService.registerUser(new User(null,username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            } else {
                System.out.println("用户名已存在");
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                request.setAttribute("Msg","用户名已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

            }
        } else {
            System.out.println("验证码不正确");
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            request.setAttribute("Msg","验证码不正确");
            try {
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    }

}