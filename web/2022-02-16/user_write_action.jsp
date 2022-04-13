<%@page import="xyz.itwill.dao.UserinfoModelOneDAO"%>
<%@page import="xyz.itwill.dto.UserinfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 회원정보를 전달받아 USERINFO 테이블에 저장하고 로그인정보 입력페이지(user_login.jsp)로 이동하는 JSP 문서 --%>    
<%
	if(request.getMethod().equals("GET")) {
		response.sendRedirect("user_error.jsp");
		return;
	}

	request.setCharacterEncoding("utf-8");
	
	String userid=request.getParameter("userid");
	String password=request.getParameter("password");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	int status=Integer.parseInt(request.getParameter("status"));
	
	UserinfoDTO userinfo=new UserinfoDTO();
	userinfo.setUserid(userid);
	userinfo.setPassword(password);
	userinfo.setName(name);
	userinfo.setEmail(email);
	userinfo.setStatus(status);
	
	int rows=UserinfoModelOneDAO.getDAO().insertUserinfo(userinfo);
	
	if(rows>0) {//회원등록이 성공한 경우
		response.sendRedirect("user_login.jsp");	
	} else {//회원등록이 실패한 경우
		session.setAttribute("message", "이미 사용중인 아이디를 입력 하였습니다.");
		session.setAttribute("userinfo", userinfo);
		response.sendRedirect("user_write.jsp");
	}
%>







