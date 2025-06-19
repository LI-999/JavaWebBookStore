package com.jakie.book.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/***
 * @Author jakie
 * @Description //TODO 封装成抽象类 提高代码复用性   以后管是哪个Servlet只需要继承该抽象类 子类编写相对应的方法即可
 * @Date 14:08 2025/6/18
 * @Param
 * @return
 **/

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("action "+action);
        //利用反射减少if else if 语句
//        if("login".equals(action)){
//            System.out.println("login处理业务");
//            login(request,response);
//        }else if("register".equals(action)){
//            System.out.println("register处理业务");
//            register(request,response);
//        }

        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            method.invoke(this, request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
