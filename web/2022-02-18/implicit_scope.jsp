<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//속성값을 저장한 객체에 따라 속성값의 사용 범위 구분
	//속성값 사용범위(Scope) : Page Scope, Request Scope, Session Scope, Application Scope
	pageContext.setAttribute("pageName", "홍길동");//Page Scope
	request.setAttribute("requestName", "임꺽정");//Request Scope
	session.setAttribute("sessionName", "전우치");//Session Scope
	application.setAttribute("applicationName", "일지매");//Application Scope
	
	//속성값을 저장하는 객체가 다른 경우 같은 속성명을 사용하여 속성값 저장 가능
	// => 동일한 객체에 같은 이름의 속성명을 사용하여 속성값을 저장할 경우 기존 속성값 대신 새로운 속성값 저장
	pageContext.setAttribute("name", "홍길동");//Page Scope
	request.setAttribute("name", "임꺽정");//Request Scope
	session.setAttribute("name", "전우치");//Session Scope
	application.setAttribute("name", "일지매");//Application Scope
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장객체 - Scope Attribute</h1>
	<hr>
	<h3>스코프 객체에 저장된 속성명이 모든 다른 경우</h3>
	<ul>
		<li>pageName 속성값(Page Scope) = ${pageName }</li>
		<li>requestName 속성값(Request Scope) = ${requestName }</li>
		<li>sessionName 속성값(Session Scope) = ${sessionName }</li>
		<li>applicationName 속성값(Application Scope) = ${applicationName }</li>
	</ul>
	<hr>
	<%-- EL 표현식의 속성명으로 속성값을 검색하는 순서 --%>
	<%-- => Page Scope >> Request Scope >> Session Scope >> Application Scope --%>
	<%-- => 스코프 객체의 속성명을 모두 다르게 선언하는 것을 권장 --%>
	<%-- 
	<h3>스코프 객체에 저장된 속성명이 모든 같은 경우</h3>
	<ul>
		<li>name 속성값(Page Scope) = ${name }</li>
		<li>name 속성값(Request Scope) = ${name }</li>
		<li>name 속성값(Session Scope) = ${name }</li>
		<li>name 속성값(Application Scope) = ${name }</li>
	</ul>
	--%>
	
	<%-- 스코프 객체에 저장된 속성명이 같은 경우 EL 표현식에서 EL 내장객체를 사용하여 \
	스코프를 구분해 속성값을 제공받아 출력 가능 --%>
	<h3>스코프 객체에 저장된 속성명이 모든 같은 경우</h3>
	<ul>
		<li>name 속성값(Page Scope) = ${pageScope.name }</li>
		<li>name 속성값(Request Scope) = ${requestScope.name }</li>
		<li>name 속성값(Session Scope) = ${sessionScope.name }</li>
		<li>name 속성값(Application Scope) = ${applicationScope.name }</li>
	</ul>
</body>
</html>
