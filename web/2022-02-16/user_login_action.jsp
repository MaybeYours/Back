<%@page import="xyz.itwill.dao.UserinfoModelOneDAO"%>
<%@page import="xyz.itwill.dto.UserinfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 로그인정보를 전달받아 USERINFO 테이블에 저장된 회원정보와 비교하여 인증 처리하는 JSP 문서 --%>
<%-- 인증 성공 : 세션에 권한 관련 정보를 저장하고 환영메세지 출력페이지(user_login.jsp)로 이동 --%>    
<%-- 인증 실패 : 로그인정보 입력페이지(user_login.jsp)로 이동 --%>    
<%
	if(request.getMethod().equals("GET")) {
		response.sendRedirect("user_error.jsp");
		return;
	}

	String userid=request.getParameter("userid");
	String password=request.getParameter("password");
	
	UserinfoDTO userinfo=UserinfoModelOneDAO.getDAO().selectUserinfo(userid);
	if(userinfo==null) {//아이디 인증 실패
		session.setAttribute("message", "입력된 아이디가 존재하지 않습니다.");
		session.setAttribute("userid", userid);
		response.sendRedirect("user_login.jsp");
		return;
	}
	
	if(!password.equals(userinfo.getPassword())) {//비밀번호 인증 실패
		session.setAttribute("message", "입력된 아이디가 잘못 되었거나 비밀번호가 맞지 않습니다.");
		session.setAttribute("userid", userid);
		response.sendRedirect("user_login.jsp");
		return;	
	}
	
	//인증 성공
	session.setAttribute("loginUserinfo", userinfo);
	response.sendRedirect("user_login.jsp");
%>











