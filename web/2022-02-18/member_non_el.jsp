<%@page import="xyz.itwill.el.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- member.jsp 문서의 Request Scope 속성값을 반환받아 클라이언트에게 전달하는 JSP 문서 - View 역활을 하는 JSP 문서 --%>
<%-- => member.jsp 문서로 요청을 받아 처리 후 member_non_el.jsp 문서로 응답 --%>
<%-- => member_non_el.jsp 문서를 요청한 경우 NullPointerException 발생 --%>
<%
	Member member=(Member)request.getAttribute("member");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 미사용</h1>
	<hr>
	<%-- <p>회원 = <%=member %></p>	--%>
	<p>회원의 이름 = <%=member.getName() %></p>	
	<%-- <p>회원의 자동차 = <%=member.getCar() %></p> --%>
	<p>회원의 자동차 모델명 = <%=member.getCar().getModelName() %></p>	
	<p>회원의 자동차 색상 = <%=member.getCar().getCarColor() %></p>	
</body>
</html>
