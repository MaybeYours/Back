<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring</title>
</head>
<body>
	<h1>회원정보변경</h1>
	<hr>
	<form action="hewon_update" method="post">
	<%-- 회원정보를 변경하기 위해 아이디를 반드시 요청 처리 메소드에 전달 --%>
	<%-- @SessionAttributes 어노테이션에 의해 제공되는 객체를 사용하므로 아이디 전달 불필요 --%>
	<%-- <input type="hidden" name="id" value="${hewon.id }"> --%>
	<table>
		<tr>
			<td>아이디</td>
			<td>${hewon.id }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${hewon.name }"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="${hewon.email }"></td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				남자<input type="radio" name="gender" value="남자" 
					<c:if test="${hewon.gender=='남자' }"> checked="checked" </c:if>>&nbsp;&nbsp;
				여자<input type="radio" name="gender" value="여자"
					<c:if test="${hewon.gender=='여자' }"> checked="checked" </c:if>>
			</td>
		</tr>
		<tr>
			<td colspan="2"><button type="submit">회원변경</button></td>
		</tr>
	</table>
	</form>
</body>
</html>


