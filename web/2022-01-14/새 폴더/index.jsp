<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 클라이언트의 모든 요청에 대한 응답 결과를 제공하기 위한 JSP 문서 - 템플릿 페이지 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style type="text/css">
div {
	margin: 5px;
	padding: 5px;
}

#header {
	height: 200px;
	border: 1px solid red;
}

#header h1 {
	text-align: center;
}

#menu {
	font-size: 1.5em;
	text-align: right;
}

a, a:visited {
	text-decoration: none;
	color: black;
}

a:hover {
	color: orange;
}

#content {
	min-height: 500px;
	border: 1px solid green;
	text-align: center;
}

#footer {
	height: 150px;
	border: 1px solid blue;
	text-align: center;
	font-size: 1.2em;
}
</style>
</head>
<body>
<div id="header">
	<h1><a href="">쇼핑몰</a></h1>
		<div id="menu">
			<a href="">제품소개</a>&nbsp;&nbsp;
			<a href="">장바구니</a>&nbsp;&nbsp;
			<a href="">주문내역</a>&nbsp;&nbsp;
			<a href="">사용후기</a>&nbsp;&nbsp;
		</div>
	</div>
	
	<%-- Content 영역 : 요청에 대한 응답 결과 출력 --%>
	<div id="content">
		<h2>메인 페이지</h2>
	</div>
	
	<div id="footer">
		<div>
			<a href="">이용약관</a>&nbsp;&nbsp;&nbsp;
			<a href="">개인정보</a>&nbsp;&nbsp;&nbsp;
			<a href="">고객센터</a>&nbsp;&nbsp;&nbsp;
		</div>
		<p>Copyright © ITWILL Corp. All Rights Reserved.</p>
	</div>
</body>
</html>