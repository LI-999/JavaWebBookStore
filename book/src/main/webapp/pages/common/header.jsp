<%--
  Created by IntelliJ IDEA.
  User: Just Lee
  Date: 2025/6/17
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String address = request.getScheme()
            +"://"
            +request.getServerName()
            +":"+request.getServerPort()
            +request.getContextPath()
            +"/";
%>
<base href="<%=address%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/lib/jquery-3.6.0.js"></script>