<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//스레드가 이동된 JSP 문서(footer.jsp)는 클라이언트의 요청 JSP 문서(index.jsp)의
	//request 내장객체와 response 내장객체를 전달받아 사용
	// => 스레드가 이동된 JSP 문서에서는 다른 JSP 문서의 내장객체를 사용하므로 사용에 제한 발생
	
	//주의)스레드가 이동된 JSP 문서에서는 request 내장객체에 대한 리퀘스트 메세지의 정보 변경 불가능
	// => request.setCharacterEncoding() 메소드를 호출하여 캐릭터셋 변경 불가능
	//해결)클라이언트의 요청 JSP 문서에서 request 내장객체에 대한 리퀘스트 메세지의 정보 변경
	//request.setCharacterEncoding("utf-8");
	String master=request.getParameter("master");
	
	if(master==null) {
		//주의)스레드가 이동된 JSP 문서에서는 response.sendError() 메소드로 에러코드
		//전달 하거나 response.sendRedirect() 메소드로 URL 주소 전달 불가능
		// => 스레드가 이동된 JSP 문서에서는 응답 결과(HTML)를 요청 JSP 문서로 제공
		//해결)response.sendRedirect() 메소드 대신 자바스트립트를 이용하여 페이지 이동 가능
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
%>    
<hr>
<p>Copyright © Itwill Corp. All rights reserved.</p>
<%-- <p>관리자 : 홍길동(hong@itwill.xyz)</p> --%>
<p>관리자 : <%=master %></p>




