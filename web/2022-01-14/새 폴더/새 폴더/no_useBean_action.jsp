<%@page import="xyz.itwill.bean.Hewon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 입력페이지(no_useBean_form.jsp)의 전달값을 반환받아 내장객체의 속성값으로 저장하고
응답페이지(no_useBean_print.jsp)로 포워드 이동하는 JSP 문서 --%>    
<%
	//비정상적으로 요청한 경우에 대한 응답 처리
	if(request.getMethod().equals("GET")) {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}

	//POST 방식으로 요청하여 전달된 값에 대한 캐릭터셋 변경
	request.setCharacterEncoding("utf-8");
	
	//전달값을 반환받아 저장
	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	String address=request.getParameter("address");
	
	//JavaBean 클래스로 인스턴스를 생성하여 전달값으로 필드값 변경
	Hewon hewon=new Hewon();
	hewon.setName(name);
	hewon.setPhone(phone);
	hewon.setAddress(address);
	
	//Java 명령을 사용하여 다른 JSP 문서로 응답되도록 구현하는 방법
	// => 리다이렉트 이동과 포워드 이동
	
	//1.리다이렉트 이동 : 클라이언트에게 URL 주소를 전달하여 다른 JSP 문서를 요청해 응답
	// => 클라이언트 브라우저의 URL 주소 변경 - 클라이언트를 이용하여 페이지 이동
	// => session 내장객체의 속성값으로 저장하여 응답 JSP 문서에서 사용 - 속성값 반환 후 제거
	//session.setAttribute("hewon", hewon);//session Scope
	//response.sendRedirect("no_useBean_print.jsp");
	
	//2.포워드 이동 : 요청 JSP 문서에서 응답 JSP 문서로 스레드를 이동하여 응답
	// => 클라이언트 브라우저의 URL 주소 미변경 - 서버 웹프로그램의 스레드를 이용하여 페이지 이동
	// => request 내장객체의 속성값으로 저장하여 응답 JSP 문서에서 사용
	request.setAttribute("hewon", hewon);//request Scope
	
	//request.getRequestDispatcher(String url) : RequestDispatcher 인스턴스를 반환하는 메소드
	//RequestDispatcher : 다른 웹프로그램(JSP)으로 스레드를 이동시키는 기능을 제공하는 인스턴스
	// => 서버에 존재하는 웹프로그램의 URL 주소만 전달받아 RequestDispatcher 인스턴스에 저장 가능
	//RequestDispatcher.forward(servletRequest request, servletResponse response) : RequestDispatcher
	//인스턴스에 저장된 URL 주소의 웹프로그램으로 스레드를 이동하여 응답하는 메소드 
	// => forword 태그 대신 포워드 이동하는 Java 명령
	request.getRequestDispatcher("no_useBean_print.jsp").forward(request, response);
%>
