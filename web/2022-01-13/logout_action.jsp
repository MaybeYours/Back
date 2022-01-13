<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 로그아웃 처리 후 로그인 입력페이지(login_form.jsp)로 이동하는 JSP 문서 --%>
<%
	//로그아웃 처리 : 세션의 속성값으로 저장된 권한 관련 정보를 제거
	//session.removeAttribute("loginId");
	session.invalidate();//세션을 언바인딩 처리하고 제거하는 메소드

	response.sendRedirect("login_form.jsp");
%>