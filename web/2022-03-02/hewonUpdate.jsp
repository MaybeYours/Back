<%@page import="xyz.itwill.dao.MyHewonDAO"%>
<%@page import="xyz.itwill.dto.MyHewon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//아이디가 [xxx]인 회원의 이름을 [로빈훗]으로 변경
	/*
	MyHewon hewon=new MyHewon();//MyHewon 인스턴스에 필드에는 기본값 저장
	hewon.setId("xxx");
	hewon.setName("로빈훗");
	
	//DAO 클래스의 메소드에는 회원정보의 아이디와 이름만 전달
	// => 아이디와 이름을 제외한 필드는 기본값 전달
	// => MYHEWON 테이블에 저장된 회원정보가 비정상적으로 변경
	MyHewonDAO.getDAO().updateHewon(hewon);
	*/

	MyHewon hewon=new MyHewon();
	hewon.setId("xxx");
	hewon.setName("로빈훗");
	//변경되지 않는 컬럼도 기존 컬럼값이 회원정보로 저장되도록 필드값 변경
	hewon.setPhone("010-7841-1211");
	hewon.setEmail("xxx@itwill.xyz");
	hewon.setState(4);
	
	MyHewonDAO.getDAO().updateHewon(hewon);

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYBATIS</title>
</head>
<body>
	<h1>회원정보변경</h1>
	<hr>
	<h3>회원정보를 성공적으로 변경 하였습니다.</h3>
</body>
</html>