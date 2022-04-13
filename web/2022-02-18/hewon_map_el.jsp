<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL - List</h1>
	<hr>
	<%-- 스코프 객체의 속성값으로 저장된 Map 인스턴스의 맵키가 EL 표현식으로 사용하기 
	부적절한 경우 . 연산자로 맵값을 제공받아 출력 불가능 --%>
	<%-- =>  Map 인스턴스의 맵키가 EL 표현식으로 사용하기 부적절한 경우 [] 연산자
	안에 맵키를 사용하여 맵값을 제공받아 출력 가능 --%>
	<%-- <p>이름 = ${hewonMap.first.name} ${hewonMap.second.name}</p> --%>
	<p>이름 = ${hewonMap["first.name"]} ${hewonMap["second.name"]}</p>
</body>
</html>






