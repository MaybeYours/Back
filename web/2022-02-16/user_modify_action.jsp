<%@page import="xyz.itwill.dao.UserinfoModelOneDAO"%>
<%@page import="xyz.itwill.dto.UserinfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 회원정보를 전달받아 USERINFO 테이블에 저장된 회원정보를 변경하고 회원정보 
출력페이지(user_view.jsp)로 이동(아이디 전달)하는 JSP 문서 --%>    
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
	//비밀번호가 전달되지 않은 경우 - 기존 비밀번호로 변경
	if(password==null || password.equals("")) {
		userinfo.setPassword(UserinfoModelOneDAO.getDAO().selectUserinfo(userid).getPassword());
	} else {//비밀번호가 전달된 경우 - 새로운 비밀번호로 변경
		userinfo.setPassword(password);
	}
	userinfo.setName(name);
	userinfo.setEmail(email);
	userinfo.setStatus(status);
	
	UserinfoModelOneDAO.getDAO().updateUserinfo(userinfo);
	
	response.sendRedirect("user_view.jsp?userid="+userid);	

%>