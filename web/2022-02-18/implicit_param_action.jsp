<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달값을 반환받아 클라이언트에게 전달하는 JSP 문서 --%>
<% request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장객체 - Request Parameter</h1>
	<hr>
	<h3>EL 미사용</h3>
	<ul>
		<li>이름 = <%=request.getParameter("name") %></li>
		<li>주소 = <%=request.getParameter("address") %></li>
		<%-- 같은 이름으로 전달되는 값이 여러개 있는 경우 첫번째 전달값을 반환받아 사용 --%>
		<%-- 
		<li>음식-1 = <%=request.getParameter("food") %></li>
		<li>음식-2 = <%=request.getParameter("food") %></li>
		--%>
		<%-- 같은 이름으로 전달되는 값이 여러개 있는 경우 request.getParameterValues(String name)
		메소드를 호출하여 문자열 배열로 반환받아 사용 가능 --%>
		<li>음식-1 = <%=request.getParameterValues("food")[0] %></li>
		<li>음식-2 = <%=request.getParameterValues("food")[1] %></li>
	</ul>
	<hr>
	<%-- EL 표현식에서 param 내장객체 또는 paramValues 내장객체를 이용하여 전달값을
	제공받아 출력 가능 - 전달값의 이름을 이용하여 전달값 구분 --%>
	<h3>EL 사용</h3>
	<ul>
		<li>이름 = ${param.name }</li>
		<li>주소 = ${param.address }</li>
		<li>음식-1 = ${paramValues.food[0] }</li>
		<li>음식-2 = ${paramValues.food[1] }</li>
	</ul>
</body>
</html>















