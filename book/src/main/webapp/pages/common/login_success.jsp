<%--
  Created by IntelliJ IDEA.
  User: Just Lee
  Date: 2025/6/17
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div>
  <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
  <a href="pages/order/order.jsp">我的订单</a>
  <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
  <a href="index.jsp">返回</a>
</div>
