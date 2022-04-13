<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//HashMap(Map) 인스턴스를 생성하여 저장
	Map<String,String> carMap=new HashMap<String,String>();

	//HashMap(Map) 인스턴스에 엔트리(Entry)를 추가
	carMap.put("modelName", "싼타페");
	carMap.put("carColor", "하얀색");
	
	//스코프 객체에 속성명과 속성값(Map 인스턴스) 저장
	pageContext.setAttribute("car", carMap);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL(Expression Language)</h1>
	<hr>	
	<h2>EL 미사용</h2>
	<%
		//@SuppressWarnings : 경고를 제거하는 어노테이션
		@SuppressWarnings("unchecked")
		//스코프 객체에 저장된 속성값(Map 인스턴스)을 반환받아 저장
		Map<String,String> car=(Map<String,String>)pageContext.getAttribute("car");
	%>
	<%-- Map 인스턴스에 저장된 맵값을 맵키를 이용하여 반환받아 출력 --%>
	<p>자동차 모델명 = <%=car.get("modelName") %></p>	
	<p>자동차 색상 = <%=car.get("carColor") %></p>	
	<hr>
	<h2>EL 사용</h2>
	<%-- 스코프 객체에 저장된 속성값이 Map 인스턴스인 경우 ${속성명.맵키} 형식의 표현식을
	사용하여 Map 인스턴스의 맵값을 제공받아 출력 --%>
	<p>자동차 모델명 = ${car.modelName }</p>	
	<p>자동차 색상 = ${car.carColor }</p>
</body>
</html>






