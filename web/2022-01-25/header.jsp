<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	//세션에 저장된 권한 관련 정보를 반환받아 저장
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
	
	//비로그인 사용자이거나 관리자가 아닌 사용자가 JSP 문서를 요청한 경우 에러페이지로 이동
	if(loginMember==null || loginMember.getStatus()!=9) {
		out.println("<script type='text/javascript'>");
		out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=error&work=error400';");
		out.println("</script>");
		return;			
	}
--%>
<%@include file="/site/security/admin_check.jspf"%>

<div id="profile">
	관리자[<%=loginMember.getName() %>]님, 환영합니다.&nbsp;&nbsp;
	<a href="index.jsp?workgroup=member&work=member_logout_action">로그아웃</a>&nbsp;&nbsp;
	<a href="index.jsp?workgroup=product&work=product_list">쇼핑몰</a>&nbsp;&nbsp;
</div>
<div id="logo"><a href="index.jsp?workgroup=admin&work=admin_main">관리자</a></div>
<div id="menu">
	<a href="index.jsp?workgroup=admin&work=member_manager">회원관리</a>
	<a href="index.jsp?workgroup=admin&work=product_manager">제품관리</a>
	<a href="index.jsp?workgroup=admin&work=order_manager">주문관리</a>
	<a href="index.jsp?workgroup=admin&work=board_manager">게시글관리</a>
</div>