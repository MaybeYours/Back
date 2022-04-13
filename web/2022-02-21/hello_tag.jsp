<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSP 문서에서 커스텀 태그를 사용하고자 할 경우 taglib 디렉티브로 TLD 파일을 제공받아야 사용 가능 --%>
<%-- taglib Directive : TLD 파일을 불러와 커스텀 태그(EL 함수)를 사용하도록 제공하는 지시어 --%>
<%-- prefix 속성 : 커스텀 태그에 대한 접두사(NameSpace)를 속성값으로 설정 --%>
<%-- uri 속성 : TLD 파일의 식별자(uri 엘리먼트의 값)을 속성값으로 설정 --%>
<%@taglib prefix="simple" uri="http://www.itwill.xyz/mvc/custom" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Custom Tag - NoAttribute And NoBody</h1>
	<hr>
	<!-- 태그내용이 있는 경우 시작태그와 종료태그를 따로 작성 -->
	<%-- <simple:hello></simple:hello> --%>
	<!-- 태그내용이 없는 경우 시작태그와 종료태그를 같이 작성 -->
	<simple:hello/>
	<simple:hello/>
	<simple:hello/>
	<simple:hello/>
	<simple:hello/>
</body>
</html>