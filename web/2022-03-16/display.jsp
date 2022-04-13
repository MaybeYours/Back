<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring</title>
</head>
<body>
	<h1>리소스 파일</h1>
	<hr>
	<%-- 리소스 파일 : JSP 문서에서 HTML 태그를 이용하여 사용하는 멀티미디어 파일
	, CSS 파일, JavaScript Source 파일 등 --%>
	
	<%-- SpringMVC에서는 클라이언트의 모든 요청은 Front Controller에 의해 처리되어 응답 --%>
	<%-- 문제점)HTML 태그로 리소스 파일을 요청할 경우 리소스 파일도 Front Controller에게 
	요청되므로 매핑된 요청 처리 메소드가 없어 404 에러코드 발생 --%>
	<%-- 해결법)HTML 태그로 리소스 파일을 요청할 경우 Front Controller를 이용하지 않고 직접 응답되도록 처리 --%>
	<%-- => Front Controller의 Bean Configuration File(servlet-context.xml)에서 
	resources 엘리먼트를 사용하여 직접 응답되도록 설정 --%>
	<%-- <img src="resources/images/Koala.jpg" width="200"> --%>
	<img src="images/Koala.jpg" width="200">
</body>
</html>