<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 입력페이지(login_form.jsp)에서 전달된 아이디와 비밀번호를 반환받아 인증처리(아이디와 
비밀번호를 비교하는 작업)하는 JSP 문서 - 처리페이지 --%>
<%-- => 인증 실패 : 입력페이지(login_form.jsp)로 이동 --%>    
<%-- => 인증 성공 : 출력페이지(login_user.jsp)로 이동 --%>    
<%
	//비정상적인 요청에 대한 응답 처리
	if(request.getMethod().equals("GET")) {//JSP 문서(login_action.jsp)를 GET 방식으로 요청한 경우
		//세션에 메세지를 속성값으로 저장하고 입력페이지(login_form.jsp)로 이동
		session.setAttribute("message", "비정상적인 방법으로 페이지를 요청 하였습니다.");
		response.sendRedirect("login_form.jsp");
		return;
	}

	//전달값을 반환받아 저장
	String id=request.getParameter("id");
	String passwd=request.getParameter("passwd");
	
	//아이디와 비밀번호를 비교 - 인증처리
	//인증실패 : 세션에 메세지와 아이디를 속성값으로 저장하고 입력페이지(login_form.jsp)로 이동
	if(!id.equals("abc123") || !passwd.equals("123456")) {
		session.setAttribute("message", "아이디 혹은 비밀번호가 일치하지 않습니다.");
		session.setAttribute("id", id);
		response.sendRedirect("login_form.jsp");
		return;
	}
	
	//인증성공 : 세션에 권한 관련 정보를 속성값으로 저장하고 출력페이지(login_user.jsp)로 이동
	session.setAttribute("loginId", id);
	response.sendRedirect("login_user.jsp");
%>








