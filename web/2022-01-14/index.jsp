<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	//String headerFileName="header.jspf";
	String headerFileName="header.jsp";
	
	//JSP 문서 요청시 전달된 값을 반환받아 저장
	String category=request.getParameter("category");
	if(category==null) {//전달값이 없는 경우
		category="main";
	}
	
	String master="";
	
	//반환받은 전달값을 비교하여 Header 영역에 포함될 JSP 문서의 파일명과 관리자 정보 변경
	if(category.equals("main")) {
		headerFileName="header_main.jsp";
		master="홍길동(hong@itwill.xyz)";
	} else if(category.equals("blog")) {
		headerFileName="header_blog.jsp";
		master="임꺽정(lim@itwill.xyz)";
	} else if(category.equals("cafe")) {
		headerFileName="header_cafe.jsp";
		master="전우치(jwc@itwill.xyz)";
	} else {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<%-- Header 영역 --%>
	<%-- 
	<h1>메인 페이지</h1>
	<a href="index.jsp?category=main">메인</a>&nbsp;&nbsp;
	<a href="index.jsp?category=blog">블로그</a>&nbsp;&nbsp;
	<a href="index.jsp?category=cafe">카페</a>&nbsp;&nbsp;
	<hr>
	--%>
	
	<%-- include Directive : 외부파일(JSPF)의 코드(HTML 태그, Java 명령 등) 포함 --%>
	<%-- => JSP 문서에 외부파일의 코드를 포함하여 페이지 구현 --%>
	<%-- => 외부파일의 코드가 변경될 경우 JSP 문서가 변경된 것과 동일하므로 현재 JSP 문서에 대한 서블릿 변환 발생 --%>
	<%-- => file 속성값으로 표현식(Expression) 사용 불가능 - 속성값으로 설정된 파일의 코드만 포함(정적포함) --%>
	<%-- <%@include file="header.jspf" %> --%>
	
	<%-- include ActionTag : 스레드가 이동된 다른 JSP 문서의 실행결과(HTML 태그) 포함 --%>
	<%-- 형식)<jsp:include page="JSP"></jsp:include> or <jsp:include page="JSP"/> --%>
	<%-- => 현재 JSP 문서에서 page 속성값으로 설정된 JSP 문서로 스레드를 이동시켜 실행결과를 
	얻어와 현재 JSP 문서에 포함하여 페이지 구현 --%>
	<%-- => page 속성값으로 설정된 JSP 문서를 변경해도 현재 JSP 문서에는 미영향 --%>
	<%-- => page 속성값으로 표현식 사용 가능 - 표현식에 사용된 변수값에 따라 다른 JSP 문서의 실행 결과 포함(동적포함) --%>
	<%-- <jsp:include page="header.jsp"/> --%>
	<%-- page 속성값으로 설정된 JSP 문서가 없는 경우 500 에러 발생 --%>
	<jsp:include page="<%=headerFileName %>"/>
	
	<%-- Content 영역 --%>
	<ul>
		<li>요청에 대한 처리 결과</li>
		<li>요청에 대한 처리 결과</li>
		<li>요청에 대한 처리 결과</li>
		<li>요청에 대한 처리 결과</li>
		<li>요청에 대한 처리 결과</li>
	</ul>
	
	<%-- Footer 영역 --%>
	<%-- 
	<hr>
	<p>Copyright © Itwill Corp. All rights reserved.</p>
	<!--  <p>관리자 : 홍길동(hong@itwill.xyz)</p> -->
	<p>관리자 : <%=master %></p>
	--%> 

	<%-- param ActionTag : 스레드가 이동되는 JSP 문서에 값을 전달하는 태그 --%>
	<%-- => 리퀘스트 바디에 전달값을 저장하여 JSP 문서에 전달 - POST 방식과 유사 --%>
	<%-- 주의) include 태그 내부에는 param 태그외 다른 코드가 존재할 경우 500 에러 발생 --%>
	<jsp:include page="footer.jsp">
		<jsp:param value="<%=master %>" name="master"/>
	</jsp:include>
</body>
</html>









