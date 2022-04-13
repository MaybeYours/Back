<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- member.jsp 문서의 Request Scope 속성값을 반환받아 클라이언트에게 전달하는 JSP 문서 - View 역활을 하는 JSP 문서 --%>
<%-- => member.jsp 문서로 요청을 받아 처리 후 member_el.jsp 문서로 응답 --%>
<%-- => member_el.jsp 문서를 요청한 경우에도 NullPointerException 미발생 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 사용</h1>
	<hr>
	<%-- <p>회원 = ${member }</p> --%>
	<%-- <p>회원의 이름 = ${member.name }</p> --%>
	<%-- EL 표현식에서 . 연산자 대신 [] 연산자를 사용하여 필드명(맵키)로 필드값(맵값)을 제공받아 출력 --%>	
	<p>회원의 이름 = ${member["name"] }</p>	
	<%-- <p>회원의 자동차 = ${member.car }</p> --%>
	<p>회원의 자동차 모델명 = ${member.car.modelName }</p>	
	<p>회원의 자동차 색상 = ${member.car.carColor }</p>		
</body>
</html>






