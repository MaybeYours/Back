<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 회원정보를 입력받기 위한 JSP 문서 --%>
<%-- => [입력완료]를 클릭한 경우 처리페이지(no_useBean_action.jsp)를 요청하여 입력값 전달 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>회원정보입력</h1>
	<hr>
	<form action="no_useBean_action.jsp" method="post">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" name="address"></td>
		</tr>
		<tr>
			<td colspan="2"><button type="submit">입력완료</button></td>
		</tr>
	</table>
	</form>
</body>
</html>