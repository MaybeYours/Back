<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="xyz.itwill.dao.MyHewonDAO"%>
<%@page import="xyz.itwill.dto.MyHewon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	
	String id="";
	if(name!=null && phone!=null) {//모든 전달값이 있는 경우
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("phone", phone);
		
		id=MyHewonDAO.getDAO().selectMapHewonId(map);
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYBATIS</title>
</head>
<body>
	<h1>아이디 찾기</h1>
	<hr>
	<form method="post" name="searchForm" id="searchForm">
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
			<td colspan="2"><button type="submit">아이디 검색</button></td>
		</tr>
	</table>
	</form>
	<hr>
	<% if(name!=null && phone!=null) { %>
		<div style="font-weight: bold; color: blue;">
		<% if(id==null || id.equals("")) { %>
			<%=name %>님의 아이디가 존재하지 않습니다.		
		<% } else {%>
			<%=name %>님의 아이디는 [<%=id %>]입니다.		
		<% } %>
		</div>
	<% } %>
	
	<script type="text/javascript">
	document.getElementById("searchForm").onsubmit=function() {
		if(searchForm.name.value=="") {
			alert("이름을 입력해 주세요.");
			return false;
		}
		
		if(searchForm.phone.value=="") {
			alert("전화번호를 입력해 주세요.");
			return false;
		}
	}
	</script>
</body>
</html>