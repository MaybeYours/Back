<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- xml_two.jsp 문서를 AJAX 기능으로 요청하여 최신 뉴스목록을 XML 형식의 텍스트 테이타로 응답받아
HTML로 변환하여 클라이언트에게 전달하는 JSP 문서 --%>
<%-- XML : 엘리먼트를 이용하여 값(열)이나 정보(행)를 구분하는 구조적인 테이타 표현 방법 --%>    
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
	<h1>헤드라인 뉴스(XML)</h1>
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
		sendRequest("GET", "xml_two.jsp", null, displayNewsList);
		document.getElementById("newsContent").style.display="block";
	}
	
	document.getElementById("newsList").onmouseout=function() {
		document.getElementById("newsContent").style.display="none";
	}
	
	function displayNewsList() {
		if(xhr.readyState==4) {
			if(xhr.status==200) {
				//XML 텍스트 데이타로 응답받은 결과값은 XMLHttpRequest 객체의 responseXML 속성을
				//이용하여 XMLDocument 객체로 반환받아 저장
				var xmlDoc=xhr.responseXML;
				//alert(xmlDoc);//[object XMLDocument]
				
				//Document.getElementsByTagName(tagName) : 태그명을 이용하여 태그를 검색해 
				//Element 객체들이 저장된 HTMLCollection(NodeList) 객체로 반환하는 메소드
				var newsList=xmlDoc.getElementsByTagName("news");
				//alert(newsList);//[object HTMLCollection]
				//alert(newsList.length);
				
				var html="<ol>";
				for(i=0;i<newsList.length;i++) {
					//NodeList.item(index) : NodeList 객체에서 index 위치의 Element 객체를 반환하는 메소드
					var news=newsList.item(i);		
					//alert(news);//[object Element]
					
					//Element 객체의 자식 엘리먼트를 검색하여 노드값을 제공받아 저장
					var headline=news.getElementsByTagName("headline").item(0).firstChild.nodeValue;
					var publisher=news.getElementsByTagName("publisher").item(0).firstChild.nodeValue;
					
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








