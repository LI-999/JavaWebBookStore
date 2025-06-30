<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--使用静态包含替换--%>
    <%@include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {

            $("a.delete_book").click(function () {

                // this对象是响应当前事务的dom对象
                console.log($(".searchPageBtn")[0]);
                return confirm("是否删除" + $(this).parent().parent().find("td:first").text());
            })

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

                location.href = "${pageScope.url}manager/bookServlet?action=page&pageNo=" + $pageNo;
            })
        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo2.png">
    <span class="wel_word">图书管理系统</span>
    <%--		静态包含--%>
    <%@ include file="/pages/common/book_manage.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="delete_book" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>

    <%@ include file="/pages/common/page_nav.jsp"%>
</div>


<%--		静态包含--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>