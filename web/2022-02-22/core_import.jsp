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
	<h1>Core - URL 관리 태그</h1>
	<hr>
	<%-- import 태그 : 다른 웹프로그램의 실행 결과를 응답받아 포함하는 기능을 제공하는 태그 --%>
	<%-- => JSP의 include 태그와 유사한 기능을 제공 --%>
	<%-- url 속성 : 응답 결과를 포함시킬 웹프로그램의 URL 주소를 속성값으로 설정 --%>
	<%-- <c:import url="core_import_source.jsp"/> --%>
	
	<%-- include 태그는 현재 서버의 웹프로그램만을 요청하여 실행결과를 응답받아 포함하지만
	import 태그는 다른 서버의 웹프로그램을 요청하여 실행 결과를 응답받아 포함 가능 --%>
	<%-- => HTML의 iframe 태그와 유사한 기능을 제공 --%>
	<%-- <c:import url="https://www.daum.net"/> --%>
	
	<%-- URL 관리 태그에는 param 태그를 하위태그로 사용하여 값 전달 가능 --%>
	<c:import url="core_import_source.jsp">
		<%-- param 태그 : 요청 웹프로그램에게 값을 전달하기 위한 태그 --%>
		<%-- => URL 관리 태그의 종속 태그 --%>
		<%-- => URL 관리 태그에 param 태그를 제외한 코드가 존재할 경우 에러 발생 - JSP 주석 제외 --%>
		<c:param name="name" value="홍길동"/>
	</c:import>
</body>
</html>







