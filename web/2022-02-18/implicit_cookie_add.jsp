<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//쿠키값은 문자열만 저장 가능하며 한글 저장 불가능
	Cookie cookie=new Cookie("userName","HongGilDong");
	cookie.setMaxAge(60*60);
	response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장객체 - Cookie</h1>
	<hr>
	<p>클라이언트에 쿠키가 전달되어 저장 되었습니다.</p>
	<p><a href="implicit_cookie_print.jsp">쿠키값 출력</a></p>
</body>
</html>