<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring</title>
</head>
<body>
	<h1>회원정보</h1>
	<hr>
	<ul>
		<li>아이디 = ${hewon.id }</li>
		<li>비밀번호 = ${hewon.passwd }</li>
		<li>이름 = ${hewon.name }</li>
		<li>이메일 = ${hewon.email }</li>
		<li>성별 = ${hewon.gender }</li>
	</ul>
	
	<%-- 회원정보 변경 입력페이지 이동 - 아이디 전달 --%>
	<%-- <a href="hewon_update?id=${hewon.id }">회원정보변경</a> --%>
	<%-- 아이디를 전달받아 회원정보를 검색할 필요가 없으므로 아이디 전달 불필요 --%>
	<a href="hewon_update">회원정보변경</a>
</body>
</html>