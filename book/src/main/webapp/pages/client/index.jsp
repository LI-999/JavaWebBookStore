<%--
  Created by IntelliJ IDEA.
  User: Just Lee
  Date: 2025/6/20
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/common/header.jsp" %>

    <script>
        $(function () {
            $(".searchPageBtn").click(function () {
                var $pageNo = $("#pn_input").val();
                <%--alert(${pageScope.url});--%>
                //验证输入的数值是否符合区间范围
                <%--var totalPage = ${requestScope.page.pageTotal};--%>
                <%--if($pageNo>totalPage)--%>
                <%--    $pageNo = totalPage;--%>
                <%--else if($pageNo<1)--%>
                <%--    $pageNo = 1;--%>
                <%--alert($pageNo);--%>
                <%--alert(${requestScope.page.pageNo});--%>

                location.href = "${pageScope.url}?action=page&pageNo=" + $pageNo;
            })

            $(".add_item").click(function () {
                // alert($(this).attr("book_id"));
                var $id = $(this).attr("book_id");

                location.href = "${pageScope.url}cartServlet?action=addItem&book_id=" + $id;
                console.log(location.href);
            })


        })
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo2.png">
    <span class="wel_word">网上书城</span>
    <div>
        <%--    用户为登录时的主界面--%>
        <c:if test="${empty sessionScope.user.username}">
            <%--        <c:if test="${empty cookie.username.value}">--%>
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>

        <%--  用户登录成功--%>
        <c:if test="${not empty sessionScope.user.username}">
            <%--        <c:if test="${not empty cookie.username.value}">--%>
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <%--            <span>欢迎<span class="um_span">${cookie.username.value}</span>光临尚硅谷书城</span>--%>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="userServlet?action=logout">注销</a>
        </c:if>

        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="queryPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" id="queryPrice" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${not empty sessionScope.cart.itemMap}">
                <span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span style="color: red">${sessionScope.lastAddBook}</span>加入到了购物车中
                </div>
            </c:if>


            <c:if test="${empty sessionScope.cart.itemMap}">
                <div>
                    <span style="color: red">您的购物车中为空</span>
                </div>
            </c:if>


        </div>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">

                <div class="img_div">
                    <img class="book_img" alt="" src="${book.img_path}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="add_item" book_id="${book.id}">加入购物车</button>
                    </div>
                </div>

            </div>
        </c:forEach>

    </div>

    <%@ include file="/pages/common/page_nav.jsp" %>


</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
