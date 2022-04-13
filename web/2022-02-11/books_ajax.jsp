<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- books.jsp 문서를 AJAX 기능으로 요청하여 실행결과를 XML 텍스트 데이타로 응답받아 HTML 태그로
변환하여 클라이언트에게 전달하는 JSP 문서  --%>
<%-- => books.xsl 문서를 이용하여 XML 텍스트 데이타를 HTML 태그로 변환 --%>
<%-- => ajax.js 모듈 사용 : 객체를 사용하여 AJAX 기능 제공 - 객체 속성으로 XMLHttpRequest 객체 포함 --%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
</head>
<body>
	<h1>교재목록</h1>
	<hr>
	<div id="books"></div>
	
	<script type="text/javascript">
	var xmlDoc=null;
	var xslDoc=null;
	
	function loadBooksXML() {
		//Ajax 객체 생성 - XMLHttpRequest 객체를 생성하여 웹프로그램을 비동기식으로 요청하여 결과를 응답받아 처리
		new xyz.itwill.Ajax("GET", "books.jsp", null, function(xhr) {
			if(xhr.readyState==4) {
				if(xhr.status==200) {
					xmlDoc=xhr.responseXML;
					doXSLT();
				} else {
					alert("에러코드 = "+xhr.status);
				}
			}
		}); 
	}
	
	function loadBooksXSL() {
		new xyz.itwill.Ajax("GET", "books.xsl", null, function(xhr) {
			if(xhr.readyState==4) {
				if(xhr.status==200) {
					xslDoc=xhr.responseXML;
					doXSLT();
				} else {
					alert("에러코드 = "+xhr.status);
				}
			}
		}); 
	}
	
	loadBooksXML();
	loadBooksXSL();
	
	function doXSLT() {
		if(xmlDoc==null || xslDoc==null) return;
		
		var books=document.getElementById("books");
		
		//XSLTProcessor 객체를 생성하여 저장
		//XSLTProcessor : XSLT를 이용하여 XML를 변환하는 객체
		var xsltProcessor=new XSLTProcessor();
		
		//XSLTProcessor.importStylesheet(xslt) : XSLTProcessor 객체에 변환정보(XSLT)를 변경하는 메소드
		xsltProcessor.importStylesheet(xslDoc);
		
		//XSLTProcessor.transformToFragment(xml, document) : XSLTProcessor 객체에 저장된 
		//변환정보를 이용하여 XML 텍스트 데이타를 전달받아 변환하여 Element 객체로 반환하는 메소드
		var fragment=xsltProcessor.transformToFragment(xmlDoc, document);
		
		//반환받은 Element 객체를 원하는 Element 객체의 자식 엘리먼트로 추가
		books.appendChild(fragment);
	}
	</script>
</body>
</html>








