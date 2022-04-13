<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- taglib Directive를 이용하여 JSTL의 Formatter 태그 라이브러리를 JSP 문서에 포함시켜 태그를 
사용할 수 있도록 제공 - prefix 속성값은 [fmt]로 설정 --%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Formatter - 날짜와 시간 변환 태그</h1>
	<hr>
	<c:set var="now" value="<%=new Date() %>"/>
	<p>now = ${now }</p>
	
	<%-- formatDate 태그 : 스코프 객체에 저장된 Date 인스턴스의 날자와 시간을 원하는 패턴의 
	문자열로 변환하여 제공하는 태그 - SimpleDateFormat 클래스 --%>
	<%-- value 속성 : Date 인스턴스를 속성값으로 설정 --%>
	<%-- type 속성 : date(날짜), time(시간), both(날짜와 시간) 중 하나를 속성값으로 설정 --%>
	<%-- => 속성값이 [date]인 경우 기본적으로 [yyyy.M.d.] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(날짜) = <fmt:formatDate value="${now }" type="date"/> 
	<%-- dateStyle 속성 : full 또는 short 중 하나를 속성값으로 설정 --%>
	<%-- => 속성값이 [full]인 경우 기본적으로 [yyyy년 M월 d일 E요일] 패턴의 문자열로 변환되어 제공 --%>
	<%-- => 속성값이 [short]인 경우 기본적으로 [yy.M.d.] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(날짜) = <fmt:formatDate value="${now }" type="date" dateStyle="full"/> 
	<p>now(날짜) = <fmt:formatDate value="${now }" type="date" dateStyle="short"/>
	
	<%-- type 속성 : date(날짜), time(시간), both(날짜와 시간) 중 하나를 속성값으로 설정 --%>
	<%-- => 속성값이 [time]인 경우 기본적으로 [a h:mm:ss] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(시간) = <fmt:formatDate value="${now }" type="time"/> 

	<%-- timeStyle 속성 : full 또는 short 중 하나를 속성값으로 설정 --%>
	<%-- => 속성값이 [full]인 경우 기본적으로 [a h시 mm분 ss초 z] 패턴의 문자열로 변환되어 제공 --%>
	<%-- => 속성값이 [short]인 경우 기본적으로 [a h:mm] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(시간) = <fmt:formatDate value="${now }" type="time" timeStyle="full"/> 
	<p>now(시간) = <fmt:formatDate value="${now }" type="time" timeStyle="short"/> 

	<%-- type 속성 : date(날짜), time(시간), both(날짜와 시간) 중 하나를 속성값으로 설정 --%>
	<%-- => 속성값이 [both]인 경우 기본적으로 [yyyy.M.d. a h:mm:ss] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(날짜와 시간) = <fmt:formatDate value="${now }" type="both"/> 
	
	<p>now(날짜와 시간) = <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/> 
	<p>now(날짜와 시간) = <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short"/> 
	
	<%-- pattern 속성 : 날짜와 시간 관련 패턴문자를 속성값으로 설정 --%>
	<p>now(패턴) = <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss"/>
	
	<%-- timeZone 태그 : 타임존(TimeZone)을 설정하는 태그 --%>
	<%-- value 속성 : 타임존(대륙/도시)을 속성값으로 설정 --%>
	<fmt:timeZone value="Asia/Hong_Kong">
		<p>현재 홍콩의 날짜와 시간 = <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss"/>
	</fmt:timeZone>
</body>
</html>








