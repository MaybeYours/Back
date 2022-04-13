<%@page import="xyz.itwill.el.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//스코프 객체에 속성명과 속성값(Car 인스턴스) 저장
	pageContext.setAttribute("car", new Car("싼타페","하얀색"));
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
		//스코프 객체에 속성값(Car 인스턴스)을 반환받아 저장
		Car car=(Car)pageContext.getAttribute("car");
	
		//스코프 객체에 속성명으로 저장된 속성값이 없는 경우 null 반환
		// => 참조변수에 null이 저장된 상태로 메소드를 호출할 경우 NullPointerException 발생
		//Car car=(Car)pageContext.getAttribute("pageCar");	
	
		//인스턴스의 필드값을 반환받아 저장
		String modelName=car.getModelName();	
		String carColor=car.getCarColor();
	%>	
	<p>자동차 = <%=car %></p>
	<p>자동차 모델명 = <%=modelName %></p>
	<p>자동차 색상 = <%=carColor %></p>
	<hr>
	<h2>EL 사용</h2>
	<p>자동차 = ${car }</p>
	<%-- 스코프 객체에 저장된 속성값이 인스턴스인 경우 ${속성명.필드명} 형식의 표현식을
	사용하여 인스턴스의 필드값을 제공받아 출력 --%>
	<%-- => 필드명은 Getter 메소드를 호출하기 위한 이름 --%>
	<p>자동차 모델명 = ${car.modelName }</p>
	<%-- EL 표현식에서 지정된 필드의 Getter 메소드가 없는 경우 ProperyNotFoundException 발생 --%>
	<%-- <p>자동차 모델명 = ${car.name }</p> --%>
	<p>자동차 색상 = ${car.carColor }</p>
	<%-- 속성명으로 저장된 속성값이 없는 경우 EL 미실행 - 값 미출력 --%>
	<%-- <p>자동차 모델명 = ${pageCar.modelName }</p> --%>
</body>
</html>







