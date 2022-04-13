<%@page import="xyz.itwill.el.Student"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- taglib Directive를 이용하여 JSTL의 Core 태그 라이브러리를 JSP 문서에서 포함시켜 태그를 
사용할 수 있도록 제공 - prefix 속성값은 [c]로 설정 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Core - EL 지원 태그</h1>
	<hr>
	<%-- set 태그 : 스코프 객체에 속성값을 저장하거나 변경하기 위한 태그 --%>
	<%-- var 속성 : 스코프 객체의 속성명을 속성값으로 설정 --%>
	<%-- value 속성 : 스코드 객체에 저장될 속성값(인스턴스)를 속성값으로 설정 --%>
	<%-- scope 속성 : 스코드 객체에 저장된 속성값 사용 범위를 속성값으로 설정 --%>
	<%-- => 속성값 : page(기본), request, session, application --%>
	<c:set var="su" value="10" scope="page"/>
	<p>su = ${su }</p>

	<%-- 스코프 객체의 속성명이 같은 경우 속성값 변경 --%>
	<%-- value 속성 대신 태그내용으로 스코프 객체에 저장될 속성값 설정 가능 --%>
	<c:set var="su">20</c:set>
	<p>su = ${su }</p>
	
	<%-- value 속성값으로 EL 사용 가능 - EL 표현식에서 EL 연산자 사용 가능 --%>
	<c:set var="tot" value="${su+10 }"/>
	<p>tot = ${tot }</p>
	
	<c:set var="name" value="홍길동"/>
	<p>name = ${name }</p>
	
	<c:set var="now" value="<%=new Date() %>"/>
	<%-- EL 표현식에 의해 제공된 속성값이 인스턴스인 경우 toString() 메소드 자동 호출 --%>
	<%-- => toString() 메소드가 오버라이드 선언되지 않았다면 Object 클래스의 toString() 메소드 호출 --%>
	<p>now = ${now }</p>
	<%-- EL 표현식에서 의해 제공된 속성값이 인스턴스인 경우 값을 반환하는 메소드를 호출 가능 --%>
	<p>now.getTime() = ${now.getTime() }</p>
	<p>now.time = ${now.time }</p>
	
	<c:set var="student" value="<%=new Student() %>"/>
	<p>student = ${student }</p>
	<p>학번 = ${student.num }, 이름 = ${student.name }</p>
	
	<%-- set 태그를 사용하여 인스턴스의 필드값 변경 가능 --%>
	<%-- target 속성 : 스코프 객체에 저장된 속성값(인스턴스)을 속성값으로 설정 - EL --%>
	<%-- property 속성 : 필드값을 변경할 인스턴스의 필드명을 속성값으로 설정 --%>
	<c:set target="${student}" property="num" value="1000"/>
	<c:set target="${student}" property="name" value="홍길동"/>
	<p>학번 = ${student.num }, 이름 = ${student.name }</p>
	
	<%-- remove 태그 : 스코프 객체에 저장된 속성값을 제거하는 태그 --%>
	<c:remove var="student"/>
	<p>student = ${student }</p>
</body>
</html>







