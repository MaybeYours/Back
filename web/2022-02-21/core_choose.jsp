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
	<c:set var="choice" value="4"/>
	
	<%-- choose 태그 : 조건에 따라 태그내용의 출력을 선택하는 상위태그 --%>
	<%-- => 하위태그를 이용하여 태그내용을 선택하여 출력 --%>
	<%-- => 하위태그 : when 태그(1개 이상), otherwise 태그(0개 또는 1개) --%>
	<%-- => choose 태그에 하위태그를 제외한 코드가 존재할 경우 에러 발생 - JSP 주석 제외 --%>
	<c:choose>
		<%-- when 태그 : 조건에 따라 태그내용을 출력을 선택하는 태그 --%>
		<%-- test 속성 : false 또는 true 중 하나를 속성값으로 설정 --%>
		<%-- => 속성값이 거짓(false)인 경우 태그내용을 출력하지 않고 참(true)인 경우 태그내용 출력 --%>
		<c:when test="${choice==1 }">
			<p>수성으로 이동합니다.</p>
		</c:when>
		<c:when test="${choice==2 }">
			<p>금성으로 이동합니다.</p>
		</c:when>
		<c:when test="${choice==3 }">
			<p>화성으로 이동합니다.</p>
		</c:when>
		<%-- otherwise 태그 : 모든 when 태그의 조건이 거짓인 경우 태그내용을 출력하는 태그 --%>
		<c:otherwise>
			<p>지구로 이동합니다.</p>
		</c:otherwise>
	</c:choose>
	<hr>
	<c:set var="num" value="10"/>
	<c:choose>
		<c:when test="${num%2!=0 }">${num } = 홀수</c:when>
		<c:otherwise>${num } = 짝수</c:otherwise>
	</c:choose>
</body>
</html>







