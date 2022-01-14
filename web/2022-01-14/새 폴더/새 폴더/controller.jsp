<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//전달값을 반환받아 저장
	String category=request.getParameter("category");

	if(category==null) {
		//리다이렉트 이동 : 클라이언트에게 URL 주소를 전달하여 웹프로그램을 요청해
		//처리 결과를 출력(응답)하도록 구현 - 클라이언트 브라우저의 URL 주소 변경
		response.sendRedirect("main.jsp");
		return;
	}
	
	//전달값을 이용하여 JSP 문서 경로를 생성하여 저장
	String forwardFilePath=category+".jsp";
%>    
<%-- forward 태그 : 현재 JSP 문서에서 page 속성값으로 설정된 JSP 문서로 스레드를 이동시켜
명령 실행 후 처리 결과를 클라이언트에게 전달(응답)하도록 구현 - 포워드 이동 --%>
<%-- 형식) <jsp:forward page="JSP"></jsp:forward> or <jsp:forward page="JSP"/> --%>
<%-- => page 속성값으로 표현식 사용 가능 --%>
<%-- 포워드 이동 : 요청 JSP 문서에서 응답 JSP 문서로 스레드 이동 --%>
<%-- => 클라이언트와 상관없이 서버의 웹프로그램에서 이동 - 클라이언트 브라우저의 URL 주소 미변경 --%>
<jsp:forward page="<%=forwardFilePath %>"/>













