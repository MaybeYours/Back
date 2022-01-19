<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 장바구니에 저장된 제품구매목록을 클라이언트에게 전달하는 JSP 문서 --%>
<%-- => 로그인 사용자만 요청 가능한 JSP 문서 --%>
<%-- => 비로그인 사용자가 JSP 문서를 요청한 경우 로그인정보 입력페이지(member_login.jsp)로 이동 --%>
<%-- => 비로그인 사용자가 로그인 성공 후 장바구니 페이지로 이동되도록 세션에 기존 요청 
페이지의 URL 주소를 저장 --%>
<%
	//세션에 저장된 권한 관련 정보를 반환받아 저장
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");

	if(loginMember==null) {//비로그인 사용자인 경우
		//request.getRequestURI() : 클라이언트가 요청한 URI 주소를 반환하는 메소드
		//ex) http://localhost:8000/jsp/site/index.jsp?workgroup=cart&work=cart_list
		// => URI 주소 - http://localhost:8000/jsp/site/index.jsp
		// => request.getRequestURI() : /jsp/site/index.jsp
		String requestURI=request.getRequestURI();
		//System.out.println("requestURI = "+requestURI);
		
		//request.getQueryString() : 클라이언트가 요청한 URL 주소의 QueryString을 반환하는 메소드
		//ex) http://localhost:8000/jsp/site/index.jsp?workgroup=cart&work=cart_list
		// => request.getQueryString() : workgroup=cart&work=cart_list
		String queryString=request.getQueryString();
		//System.out.println("queryString = "+queryString);
		
		if(queryString==null || queryString.equals("")) {
			queryString="";	
		} else {
			queryString="?"+queryString;
		}
		
		//세션에 요청 페이지의 URL 주소를 저장
		session.setAttribute("url", requestURI+queryString);
		
		out.println("<script type='text/javascript'>");
		out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=member&work=member_login';");
		out.println("</script>");
		return;
	}
%>    
<h1>장바구니 목록</h1>












