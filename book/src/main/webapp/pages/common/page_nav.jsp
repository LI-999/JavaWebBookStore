<%--
  Created by IntelliJ IDEA.
  User: Just Lee
  Date: 2025/6/20
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="page_nav">
  <a href="${requestScope.page.url}">首页</a>
  <c:if test="${requestScope.page.pageNo>1}">
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>
  </c:if>

  【${requestScope.page.pageNo}】
  <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
  </c:if>
  共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
  到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
  <input class="searchPageBtn" type="button" value="确定">


</div>
