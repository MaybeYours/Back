<%@page import="xyz.itwill.dao.ZipDAO"%>
<%@page import="xyz.itwill.dto.ZipDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 동이름을 입력하여 전달받아 ZIP 테이블에 저장된 우편번호정보를 검색하여 클라이언트에게
우편번호와 기본주소를 전달하는 JSP 문서 --%>
<%-- => [기본주소]를 클릭한 경우 부모창의 입력태그(우편번호, 기본주소)에 값 전달 --%>
<%
	//POST 방식으로 요청하여 전달된 값에 대한 캐릭터셋 변경
	request.setCharacterEncoding("utf-8");

	List<ZipDTO> zipList=null;
	if(request.getParameter("dong")!=null) {//전달값이 있는 경우
		//동이름을 반환받아 ZIP 테이블에 저장된 해당 동이름의 우편정보정보를 검색하여 반환하는 DAO 클래스의 메소드 호출 
		zipList=ZipDAO.getDAO().selectZipList(request.getParameter("dong"));
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style type="text/css">
#search {
	width: 500px;
	margin: 0 auto;
	text-align: center;
}

table {
	border: 1px solid black;
	border-collapse: collapse;
	margin: 10px;
	font-size: 14px;
}

td { 
	border: 1px solid black; 
}

.zipcode { 
	width: 100px; 
}

.address {
	width: 400px;
	text-align: left;
	padding-left: 2px;
}
</style>
</head>
<body>
	<div id="search">
		<form name="postForm" method="post">
			동이름 : <input type="text" name="dong">
			<button type="button" id="searchBtn">주소검색</button>
		</form>
		
		<%-- 검색된 우편번호정보를 클라이언트에게 전달 --%>
		<% if(zipList!=null && !zipList.isEmpty()) {//검색결과가 있는 경우 %>
			<table>
				<tr>
					<td class="zipcode">우편번호</td>
					<td class="address" style="text-align: center;">기본주소</td>
				</tr>
				
				<% for(ZipDTO zip:zipList) { %>
				<tr>
					<td class="zipcode"><%=zip.getZipcode() %></td>
					<td class="address">
						<%-- a 태그의 href 속성값으로 자바스크립트를 작성할 경우 반드시 
						javascript 접두사(네임스페이스 - NameSpace) 사용 --%>
						<a href="javascript:searchClose('<%=zip.getZipcode()%>','<%=zip.getAddress() %>');">
							<%=zip.getAddress() %>
						</a>
					</td>
				</tr>	
				<% } %>
			</table>
		<% } %>
	</div>	
	
	<script type="text/javascript">
	postForm.dong.focus();
	
	document.getElementById("searchBtn").onclick=function() {
		if(postForm.dong.value=="") {
			postForm.dong.focus();
			return;
		}
		
		postForm.submit();
	}	
	
	function searchClose(code, addr) {
		opener.joinForm.zipcode.value=code;
		opener.joinForm.address1.value=addr;
		window.close();
	}
	</script>
</body>
</html>