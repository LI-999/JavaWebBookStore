package com.jakie.book.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    /***
     * @Author jakie
     * @Description //TODO 拦截未登录访问 通过pages/manager访问 以及 BookServlet/manager?action=page访问
     * @Date 16:14 2025/6/29
     * @Param [request, response, chain]
     * @return void
     **/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Object user = httpServletRequest.getSession().getAttribute("user");
        System.out.println("ManagerFilter中的doFilter方法");
        if(user==null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        
        chain.doFilter(request,response);
    }
}
