package com.jakie.book.filter;

import com.jakie.book.utils.DruidUtil;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            chain.doFilter(request,response);
            DruidUtil.closeConnectionAndCommit();
        } catch (Exception e) {
            DruidUtil.closeConnectionAndRollback();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
