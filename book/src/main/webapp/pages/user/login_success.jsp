<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
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
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo2.png" >
			<%--		静态包含--%>
			<%@ include file="/pages/common/login_success.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="/book/index.jsp">转到主页</a></h1>
	
		</div>

		<%--		静态包含--%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>