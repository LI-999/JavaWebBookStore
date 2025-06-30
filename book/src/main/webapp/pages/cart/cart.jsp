<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--使用静态包含替换--%>
    <%@include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function(){
            $(".delete_cartItem").click(function(){
                var $item_name= $(this).parent().parent().find("td:first").text();
                // alert($item_name);
                return confirm("是否删除"+$item_name);
            })

            $("#clear_cart").click(function(){
                return confirm("是否清空购物车");
            })


            $("#book_quantity").change(function(){
                //未修改前的值
                var $before_count = $(this).defaultValue;

                //修改过后的值
                var $after_count = $(this).val();
                // alert($count);

                //获取书名
                var $book_name = $(this).parent().siblings("td:first").text();
                // alert($book_name);

                var $book_id = $(this).attr("book_id");
                // alert($book_id);

                if(confirm("是否将 "+$book_name+" 的数量修改为"+$after_count)){
                    location.href="cartServlet?action=updateCount&newCount="+$after_count+"&book_id="+$book_id;
                }else{
                    return false;
                }
            })

        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo2.png">
    <span class="wel_word">购物车</span>
    <%--		静态包含--%>
    <%@ include file="/pages/common/login_success.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${sessionScope.cart.itemMap}" var="item">
            <tr>
                <td>${item.value.name}</td>
                <td><input id="book_quantity" book_id="${item.value.id}" style="width: 80px" type="text" value="${item.value.count}"></td>
                <td>${item.value.price}</td>
                <td>${item.value.totalPrice}</td>
                <td ><a class="delete_cartItem" href="cartServlet?action=deleteItem&book_id=${item.value.id}" >删除</a></td>
            </tr>
        </c:forEach>

        <c:if test="${empty sessionScope.cart.itemMap}">
            <tr>
                <td colspan="5"><a href="index.jsp">亲,购物车空空如也！！！赶快去购物吧</a></td>
            </tr>
        </c:if>
    </table>

    <c:if test="${not empty sessionScope.cart.itemMap}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span
                    class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clear_cart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>





</div>

<%--		静态包含--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>