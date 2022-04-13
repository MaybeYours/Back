<%@page import="xyz.itwill.dao.MyMemberXMLDAO"%>
<%@page import="xyz.itwill.dto.MyMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	String email=request.getParameter("email");
	
	MyMember member=new MyMember();
	member.setId(id);
	member.setName(name);
	member.setPhone(phone);
	member.setEmail(email);
	
	try {
		//맵퍼에 등록된 SQL 명령을 전달하여 실행할 경우 SQL 명령에 문제가 발생되면
		//PersistenceException 발생 - 예외 처리
		MyMemberXMLDAO.getDAO().insertMember(member);
		response.sendRedirect("memberDisplay.jsp");
	} catch(Exception e) {
		//response.sendRedirect("memberInputForm.jsp");
		
		out.println("<script>");
		out.println("alert('이미 사용중인 아이디를 입력하여 회원가입이 실패 하였습니다.');");
		out.println("history.back()");//out.println("history.go(-1);");
		out.println("</script>");
	}
	
%>