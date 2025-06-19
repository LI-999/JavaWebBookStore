<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%--使用静态包含替换--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo2.png" >
			<span class="wel_word">编辑图书</span>
			<%--		静态包含--%>
			<%@ include file="/pages/common/book_manage.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/bookServlet">
<%--				<input type="hidden" name="action" value="${requestScope.action==null?"add":requestScope.action}">--%>
				<input type="hidden" name="action" value="${empty param.id?"add":"update"}">
<%--	获取要更新的图书id--%>
<%--				<p>requestScope.book.id${requestScope.update_book.id} param.id:${param.id}</p>--%>
				<input type="hidden" name="id" value="${param.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>

						<td><input name="name" type="text" value="${requestScope.update_book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.update_book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.update_book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.update_book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.update_book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%--		静态包含--%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>