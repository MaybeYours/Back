<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 회원정보변경 또는 회원탈퇴를 위해 비밀번호를 입력하는 JSP 문서 --%>
<%-- => 비로그인 사용자가 JSP 문서를 요청한 경우 비정상적인 요청이므로 에러페이지로 이동 --%>
<%-- => 전달값에 비교하여 form 태그의 요청 JSP 문서를 구분 --%>
<%@include file="/site/security/login_check.jspf" %>
<%
	if(request.getParameter("action")==null) {
		out.println("<script type='text/javascript'>");
		out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=error&work=error400';");
		out.println("</script>");
		return;
	}

	//전달값을 반환받아 저장
	String action=request.getParameter("action");
	
	//전달값이 없거나 비정상적인 값이 전달된 경우 에러페이지로 이동
	if(action==null || !action.equals("modify") && !action.equals("remove")) {
		out.println("<script type='text/javascript'>");
		out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=error&work=error400';");
		out.println("</script>");
		return;
	}
	
	String message=(String)session.getAttribute("message");
	if(message==null) {
		message="";
	} else {
		session.removeAttribute("message");
	}
%>

<% if(action.equals("modify")) { %>
	<p>회원정보변경을 위해 비밀번호를 입력해 주세요.</p>
<% } else { %>
	<p>회원탈퇴를 위해 비밀번호를 입력해 주세요.</p>
<% } %>
<form name="passwordForm" method="post">
	<p>
		<input type="password" name="passwd">
		<button type="button" onclick="submitCheck();">입력완료</button>
	</p>		
</form>
<p id="message" style="color: red;"><%=message %></p>

<script type="text/javascript">
passwordForm.passwd.focus();

function submitCheck() {
	if(passwordForm.passwd.value=="") {
		document.getElementById("message").innerHTML="비밀번호를 입력해 주세요.";
		passwordForm.passwd.focus();
		return;
	}
	
	<% if(action.equals("modify")) {//회원정보변경인 경우 %>
		passwordForm.action="<%=request.getContextPath()%>/site/index.jsp?workgroup=member&work=member_modify"
	<% } else {//회원탈퇴인 경우 %>
		passwordForm.action="<%=request.getContextPath()%>/site/index.jsp?workgroup=member&work=member_remove_action"
	<% } %>
	
	passwordForm.submit();
}
</script>










