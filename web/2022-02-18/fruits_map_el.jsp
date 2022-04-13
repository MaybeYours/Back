<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL - Map</h1>
	<hr>
	<p>과일-1 = ${fruitsMap.one }</p>
	<p>과일-2 = ${fruitsMap.two }</p>
	<p>과일-3 = ${fruitsMap.three }</p>
	<p>선택 = ${choice }</p>
	<hr>
	<h3>좋아하는 과일 = ${fruitsMap.two }</h3>
	<h3>좋아하는 과일 = ${fruitsMap["two"] }</h3>
	<%-- EL 표현식에서 다른 스코프 객체의 속성값을 제공받아 맵키로 사용할 경우 
	[] 연산자 안에 표현하여 맵값 출력 가능 --%>
	<h3>좋아하는 과일 = ${fruitsMap[choice] }</h3>
</body>
</html>







