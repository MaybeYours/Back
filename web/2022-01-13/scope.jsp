<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>객체(인스턴스) 사용범위</h1>
	<hr>
	<p>객체 사용범위(Scope) : WAS 프로그램에 의해 관리되는 JSP 문서의 내장객체를 이용하여 인스턴스를
	속성값으로 저장하여 반환받아 사용할 수 있는 범위 - 내장객체에 따라 속성값으로 저장된 인스턴스의
	사용범위가 다르게 설정</p>
	<hr>
	<ul>
		<li>page Scope : pageContext 내장객체를 이용하여 인스턴스를 속성값으로 저장
		- 인스턴스를 속성값으로 저장한 JSP 문서에서만 속성값(인스턴스)을 반환받아 사용 가능</li>
		<li>request Scope : request 내장객체를 이용하여 인스턴스를 속성값으로 저장
		- 인스턴스를 속성값으로 저장한 JSP 문서와 스레드가 이동된 JSP 문서에서 속성값을 반환받아 사용 가능</li>
		<li>session Scope : session 내장객체를 이용하여 인스턴스를 속성값으로 저장
		- 같은 세션을 바인딩한 모든 JSP 문서에서 속성값을 반환받아 사용 가능</li>
		<li>application Scope : application 내장객체를 이용하여 인스턴스를 속성값으로 저장
		- WAS에 의해 관리되는 모든 JSP 문서에서 속성값을 반환받아 사용 가능</li> 
	</ul>
	<hr>
	<p>내장객체에 인스턴스를 속성값으로 저장(변경)하는 메소드
	- 내장객체.setAttribute(String attributeName,Object attributeValue)</p>
	<p>내장객체에 속성값으로 저장된 인스턴스를 반환하는 메소드
	- 내장객체.getAttribute(String attributeName</p>
	<p>내장객체에 속성값으로 저장된 인스턴스를 제거하는 메소드
	- 내장객체.removeAttribute(String attributeName</p>
</body>
</html>







