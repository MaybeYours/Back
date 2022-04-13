<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Core - 흐름 제어 태그</h1>
	<hr>
	<c:set var="phone" value="010-1234-5678"/>
	<p>전화번호 = ${phone }</p>
	<hr>
	<%-- forTokens 태그 : 스코프 객체에 저장된 속성값을 특정 문자열로 분리하여 반복 처리하는 태그 --%>
	<%-- items 속성 : 분리하기 위한 스코프 객체의 속성값(String)을 속성값으로 설정 --%>
	<%-- delims 속성 : 분리하기 위한 구분값(String)을 속성값으로 설정 --%>
	<%-- var 속성 : 분리된 값을 반복적으로 제공받기 위한 속성명을 속성값으로 설정 --%>
	<c:forTokens items="${phone }" delims="-" var="num">
		<div>${num }</div>
	</c:forTokens>
</body>
</html>