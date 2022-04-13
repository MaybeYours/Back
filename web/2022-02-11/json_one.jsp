<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- json_two.jsp 문서를 AJAX 기능으로 요청하여 최신 뉴스목록을 JSON 형식의 텍스트 테이타로 응답받아
HTML로 변환하여 클라이언트에게 전달하는 JSP 문서 --%>
<%-- JSON : 자바스크립트 객체 표기법을 이용하여 값(열)이나 정보(행)를 구분하는 구조적인 테이타 표현 방법 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/xhr.js"></script>
<style type="text/css">
#newsList {
	width: 50%;
	margin: 0 auto;
}

#newsHead {
	padding: 5px;
	text-align: center;
	font-size: x-large;
	border: 2px solid black;
}

#newsContent {
	padding: 5px;
	font-size: medium;
	border: 2px dashed black;
	display: none;
}
</style>
</head>
<body>
	<h1>헤드라인 뉴스(JSON)</h1>
	<hr>
	<div id="newsList">
		<div id="newsHead">헤드라인 뉴스</div>
		<div id="newsContent">
			<%-- 
			<ol>
				<li>뉴스제목-1[언론사-1]</li>
				<li>뉴스제목-2[언론사-2]</li>
				<li>뉴스제목-3[언론사-3]</li>
				<li>뉴스제목-4[언론사-4]</li>
				<li>뉴스제목-5[언론사-5]</li>
			</ol>
			--%>
		</div>
	</div>
	
	<script type="text/javascript">
	document.getElementById("newsList").onmouseover=function() {
		sendRequest("GET", "json_two.jsp", null, displayNewsList);
		document.getElementById("newsContent").style.display="block";
	}
	
	document.getElementById("newsList").onmouseout=function() {
		document.getElementById("newsContent").style.display="none";
	}
	
	function displayNewsList() {
		if(xhr.readyState==4) {
			if(xhr.status==200) {
				//JSON 형식의 텍스트 데이타를 반환받아 저장
				var json=xhr.responseText;
				//alert(json);
				
				//JSON 형식의 텍스트 데이타를 자바스트립트 객체로 변환하여 저장
				//1.eval() 함수를 이용하여 자바스트립트 객체로 변환
				// => eval(code) : 문자값을 자바스크립트 명령으로 변환하는 함수
				//var newsArray=eval("("+json+")");
				//2.JSON 클래스 함수의 parse() 메소드를 이용하여 자바스트립트 객체로 변환
				// => JSON.parse(json) : JSON 형식의 문자값을 자바스크립트 객체로 변환하는 메소드
				var newsArray=JSON.parse(json);
				//alert(newsArray);
				//alert(newsArray.length);
				
				var html="<ol>";
				for(i=0;i<newsArray.length;i++) {
					//Object 객체의 속성값을 반환받아 변수에 저장
					var headline=newsArray[i].headline;
					var publisher=newsArray[i].publisher;
					
					html+="<li>"+headline+"["+publisher+"]</li>";
				}
				html+="</ol>";
				
				document.getElementById("newsContent").innerHTML=html;
			} else {
				document.getElementById("newsContent").innerHTML="에러코드 = "+xhr.status;
			}
		}
	}
	</script>
</body>
</html>








