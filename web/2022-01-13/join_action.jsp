<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 입력페이지(join_form.jsp)에서 전달된 회원정보를 반환받아 클라이언트에게 응답하는 JSP 문서 - 처리페이지 --%>
<%
	//비정상적인 요청에 대한 응답 처리 : 클라이언트에게 에러코드 전달 또는 에러페이지(입력페이지) 이동
	//request.getMethod() : JSP 문서의 요청방식(GET or POST)을 반환하는 메소드 
	if(request.getMethod().equals("GET")) {//JSP 문서를 GET 방식을 요청한 경우
		//response.sendError(int sc) : 클라이언트에게 에러코드(상태코드 : 4XX or 5XX)를 전달하는 메소드
		// => 응답 관련 상태코드는 HttpServletResponse 인터페이스의 상수를 사용하여 표현
		//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		//response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		//return;
		
		//response.sendRedirect(String url) : 클라이언트에게 301 상태코드와 URL 주소를 전달하여
		//클라이언트 브라우저의 요청 URL 주소를 변경하여 재요청하도록 지시하는 메소드 - 리다이렉트 이동
		// => 리다이렉트 이동은 ServerRoot 디렉토리를 최상위 디렉토리로 처리
		//response.sendRedirect("/jsp/error/error_405.jsp");//에러페이지 이동 - 절대경로
		//return;
		
		//웹프로그램 요청시 QueryString을 이용하여 값 전달 가능
		//String message="비정상적인 방법으로 join_action.jsp 문서를 요청 하였습니다.";
		
		//URL 주소(QueryString 포함)는 영문자,숫자,일부 특수문자를 제외한 문자 표현 불가능
		// => URL 주소로 표현 불가능한 문자는 부호화 처리하여 URL 주소로 표현 가능
		//URLEncoder.encode(String s, String enc) : 문자열을 전달받아 원하는 캐릭터셋의 부호화된
		//코드값의 문자열로 변환하여 반환하는 메소드
		//String message=URLEncoder.encode("비정상적인 방법으로 join_action.jsp 문서를 요청 하였습니다.", "utf-8");
		//response.sendRedirect("join_form.jsp?message="+message);//입력페이지 이동 - 상대경로
		//return;

		//바인딩된 세션에 에러메세지를 속성값으로 저장
		// => 동일한 세션을 바인딩한 모든 JSP 문서에서는 속성값을 반환받아 사용 - Session Scope
		//session.setAttribute(String name, Object value) : 바인딩된 세션에 속성명과 속성값을 저장(변경)하는 메소드
		session.setAttribute("message", "비정상적인 방법으로 join_action.jsp 문서를 요청 하였습니다.");
		response.sendRedirect("join_form.jsp");
		return;
	}

	//POST 방식으로 요청하여 리퀘스트 바디에 저장된 전달된 입력값에 대한 캐릭터셋 변경
	//request.setCharacterEncoding(String encoding) : POST 방식으로 요청하여 전달된 값에 대한 
	//캐릭터셋을 변경하는 메소드
	request.setCharacterEncoding("utf-8");
	
	//전달값을 반환받아 저장
	//request.getParameter(String name) : 전달값을 문자열(String)로 반환하는 메소드
	// => 입력태그의 name 속성값 또는 QueryString의 이름을 이용하여 전달값 반환
	String id=request.getParameter("id");
	String pass=request.getParameter("pass");
	String name=request.getParameter("name");
	String addr=request.getParameter("addr");
	String sex=request.getParameter("sex");
	String job=request.getParameter("job");
	//request.getParameterValues(String name) : 같은 이름으로 전달된 값을 문자열 배열로 반환하는 메소드
	String[] hobby=request.getParameterValues("hobby");
	String profile=request.getParameter("profile");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>회원정보확인</h1>
	<hr>
	<ul>
		<li>아이디 = <%=id %></li>
		<li>비밀번호 = <%=pass %></li>
		<li>이름 = <%=name %></li>
		<li>주소 = <%=addr %></li>
		<li>성별 = <%=sex %></li>
		<li>직업 = <%=job %></li>
		<% if(hobby==null) {//전달값이 없는 경우 %>
			<li>취미 = 미선택</li>
		<% } else { %>
			<li>취미 = 	
			<% for(String temp:hobby) { %>
				<%=temp %>&nbsp;
			<% } %>
			</li>
		<% } %>
		<%-- 전달값에 엔터(\n : Enter)가 있는 경우 <br> 태그로 변경하여 출력 --%>
		<li>자기소개 = <br><%=profile.replace("\n", "<br>") %></li>
	</ul>
</body>
</html>