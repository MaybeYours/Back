<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="simple" uri="http://www.itwill.xyz/mvc/custom"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Custom Tag - AnyAttribute And NoBody</h1>
	<hr>
	<%-- 커스텀 태그 속성이 생략된 경우 태그 클래스의 생성자에서 설정한 기본값을 속성값으로 자동 사용 --%>
	<%-- 커스텀 태그 속성이 필수 속성으로 설정된 경우 태그 속성을 생략하면 에러 발생 --%>
	<%-- <simple:helloMessage/> --%>
	<!-- 커스텀 태그 속성이 필수 속성으로 설정된 경우 태그 사용시 속성이 자동 완성 -->
	<%-- 커스텀 태그 속성을 사용한 경우 태그 클래스의 Setter 메소드가 자동 호출되어 속성값으로 필드값 변경 --%>
	<!-- => 태그 클래스의 Setter 메소드가 없는 경우 에러 발생 -->
	<simple:helloMessage name="홍길동"/>
	<simple:helloMessage name="임꺽정"/>
</body>
</html>