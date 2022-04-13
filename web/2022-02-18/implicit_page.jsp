<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장객체 - pageContext</h1>
	<hr>
	<h3>EL 미사용</h3>
	<%-- 
	<p>컨텍스트 경로 = <%=request.getContextPath() %></p>
	<p>요청자원 주소 = <%=request.getRequestURI() %></p>
	--%>
	<p>컨텍스트 경로 = <%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %></p>
	<p>요청자원 주소 = <%=((HttpServletRequest)pageContext.getRequest()).getRequestURI() %></p>
	<hr>
	<h3>EL 사용</h3>
	<p>컨텍스트 경로 = ${pageContext.request.contextPath }</p>
	<p>요청자원 주소 = ${pageContext.request.requestURI }</p>
</body>
</html>





