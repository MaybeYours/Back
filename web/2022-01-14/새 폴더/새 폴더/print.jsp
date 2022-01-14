<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>메인페이지</h1>

<%-- 상대경로 : 클라이언트의 요청 JSP 문서 경로를 기준으로 리소스 파일의 경로 표현 --%>
<%-- 템플릿 페이지(index.jsp)의 JSP 문서 경로를 기준으로 리소스 파일 경로 표현 --%>
<%-- => 템플릿 페이지가 아닌 스레드가 이동된 JSP 문서를 요청한 경우 404 에러코드 발생  --%>
<%-- <img src="images/Koala.jpg" width="300"> --%>

<%-- 스레드가 이동된 JSP 문서 경로를 기준으로 리소스 파일 경로 표현 --%>
<%-- => 템플릿 페이지의 JSP 문서를 요청한 경우 404 에러코드 발생  --%>
<%-- <img src="../images/Koala.jpg" width="300"> --%>

<%-- 템플릿 페이지를 이용한 사이트에서는 요청 JSP 문서 경로와 응답 JSP 문서 경로가
서로 다르므로 리소스 경로를 상대경로로 표현한 경우 404 에러코드 발생 --%>
<%-- => 리소스 경로를 절대경로로 표현하는 것을 권장  --%>

<%-- 절대경로 : 최상위 디렉토리를 기준으로 리소스 파일의 경로 표현 --%>
<%-- => 클라이언트에서 실행되는 언어(HTML,CSS,JavaScript)는 서버 디렉토리를 최상위 디렉토리로 표현 --%>
<%-- <img src="/jsp/action/template_site/images/Koala.jpg" width="300"> --%>

<%-- 컨텍스트 경로가 변경될 경우 리소스 파일의 절대경로도 변경되어 404 에러코드 발생 가능 --%>
<%-- => 컨텍스트 경로가 변경되면 리소스 파일의 절대경로도 변경되도록 설정 --%>
<%-- request.getContextPath() : 컨텍스트 경로를 반환하는 메소드 --%>
<img src="<%=request.getContextPath() %>/action/template_site/images/Koala.jpg" width="300">
