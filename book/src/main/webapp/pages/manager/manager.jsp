<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%--使用静态包含替换--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo2.png" >
			<span class="wel_word">后台管理系统</span>
		<%--		静态包含--%>
		<%@ include file="/pages/common/book_manage.jsp"%>
	</div>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>

	<%--		静态包含--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>