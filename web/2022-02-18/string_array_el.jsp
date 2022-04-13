<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL - Array</h1>
	<hr>
	<ul>
		<%-- 스코프 객체의 속성값이 배열인 경우 EL 표현식에서 . 연산자로 첨자를 표현하여 
		배열 요소값을 사용할 경우 ELException 발생 --%>
		<%-- ELException : EL 표현식이 잘못된 경우 발생되는 예외 --%>
		<%-- <li>${nameArray.0 }</li> --%>
		<%-- 스코프 객체의 속성값이 배열인 경우 EL 표현식에서 [] 안에 첨자를 표현하여 배열 요소값을 제공받아 출력 --%>
		<%-- <li>${nameArray["0"] }</li> --%>
		<li>${nameArray[0] }</li>
		<li>${nameArray[1] }</li>
		<li>${nameArray[2] }</li>
		<li>${nameArray[3] }</li>
		<li>${nameArray[4] }</li>
	</ul>
</body>
</html>








