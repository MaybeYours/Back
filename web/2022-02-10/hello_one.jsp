<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<style type="text/css">
#display {
	width: 50%;
	padding: 5px;
	margin: 10px;
	font-size: 30px;
	border: 1px solid red;
}
</style>
</head>
<body>
	<h1>AJAX</h1>
	<hr>
	<div id="display">Hello, AJAX!!!</div>
	<div>
		<button type="button" id="btn">번역하기</button>
	</div>
	<hr>
	<div>
		<a href="https://www.daum.net">다음</a>
	</div>
	
	<script type="text/javascript">
	/*
	//Element 객체의 클릭 이벤트에 대한 이벤트 처리 함수 등록
	document.getElementById("btn").onclick=function() {
		//DHTML 기능을 이용한 페이지의 요소 변경
		// => 현재 웹프로그램의 결과값을 이용하여 요소 변경
		document.getElementById("display").innerHTML="안녕하세요. 에이젝스!!!";
	}
	*/
	
	//XMLHttpRequest 객체를 저장하기 위한 전역변수 선언
	//XMLHttpRequest 객체 : 비동기식으로 웹프로그램을 요청하여 응답 받기 위한 객체 - AJAX Engine
	var xhr=null;
	
	//XMLHttpRequest 객체를 생성하여 반환하는 함수
	// => 브라우저의 종류와 버전에 따라 XMLHttpRequest 객체를 생성하는 방법이 다양하므로 함수로 선언
	function getXMLHttpRequest() {
		if(window.ActiveXObject) {//IE4~IE6
			try {
				return new ActiveXObject("msxml2.XMLHTTP");//IE5~IE6
			} catch (e) {
				try {
					return new ActiveXObject("MicrosoftXML.HTTP");//IE4
				} catch (e) {
					return null;
				}
			}
		} else if(window.XMLHttpRequest) {//IE7 이상 또는 기타 웹브라우저
			return new XMLHttpRequest();
		} else {
			return null;
		}
	}

	//Element 객체의 클릭 이벤트에 대한 이벤트 처리 함수 등록
	document.getElementById("btn").onclick=function() {
		//AJAX 기능(비동기식 통신 + DHTML)을 이용한 페이지의 요소 변경
		// => 다른 웹프로그램을 비동기식으로 요청하여 실행 결과를 응답받아 요소 변경
		
		//1.XMLHttpRequest 객체를 생성하여 저장 - 전역변수와 함수를 이용
		xhr=getXMLHttpRequest();//준비상태 : 0
		
		//2.XMLHttpRequest 객체의 준비상태(ReadyState)가 변경될 경우 호출될 이벤트 처리 함수 등록
		//XMLHttpRequest.readyState : XMLHttpRequest 객체의 준비상태 정보를 저장하는 속성(Property)
		// => XMLHttpRequest 객체의 준비상태의 순차적으로 변경
		// => 0(기본), 1(요청 초기화 : open), 2(요청 : send), 3(응답대기 : 처리), 4(응답 : 결과)
		//XMLHttpRequest.onreadystatechange : XMLHttpRequest 객체의 준비상태가 변경될 경우 호출될
		//이벤트 처리 함수를 등록하기 위한 이벤트 속성(Property)
		xhr.onreadystatechange=changHTML;//이벤트 처리 함수는 4번 호출
		
		//3.XMLHttpRequest 객체로 open 메소드 호출 - 준비상태 : 1
		//XMLHttpRequest.open(method, url[, async]) : XMLHttpRequest 객체로 웹프로그램을 요청하기
		//위한 정보를 설정하는 메소드
		// => method : 요청방식 - GET, POST, PUT, PATCH, DELETE 등
		// => url : 요청 웹프로그램의 URL 주소 - 현재 서버의 웹프로그램만 요청 가능
		// => async : 동기식 통신 또는 비동기식 통신을 구분 - false(동기식), true(비동기식 : 기본)
		xhr.open("GET", "hello_two.jsp", true);
		//xhr.open("GET", "hello_two.jsp", false);
		
		//4.XMLHttpRequest 객체로 send 메소드 호출 - 준비상태 : 2
		//XMLHttpRequest.send(data) : XMLHttpRequest 객체로 웹프로그램을 요청하기 위한 메소드
		// => data : 요청 프로그램에 전달할 값을 [이름=값&이름=값&...] 형식으로 제공
		// => POST 방식으로 요청할 경우에만 매개변수를 이용하여 전달값 제공
		// => 전달값이 없거나 GET 방식으로 요청할 경우 매개변수에는 null 전달
		xhr.send(null);//웹프로그램 호출 후 준비상태는 자동으로 [3]과 [4]로 변경
	}
	
	//XMLHttpRequest 객체의 준비상태가 변경될 경우 호출되는 이벤트 처리 함수
	// => 요청에 대한 응답 결과를 제공받아 처리하는 함수
	function changHTML() {
		/*
		if(xhr.readyState==1) {
			alert("요청 초기화 상태");
		} else if(xhr.readyState==2) {
			alert("요청 상태");
		} else if(xhr.readyState==3) {
			alert("응답 대기 상태");
		} else if(xhr.readyState==4) {
			alert("응답 상태");
		}
		*/
		
		//5.응답결과를 제공받아 DOM 조작(DHTML)
		if(xhr.readyState==4) {
			//XMLHttpRequest.status : 요청에 대한 응답코드를 저장하는 속성(Property)
			if(xhr.status==200) {//요청에 대한 정상적인 응답을 받은 경우
				//XMLHttpRequest.responseText : 응답결과(Non-XML)를 저장한 속성(Property)
				//XMLHttpRequest.responseXML : 응답결과(XML)를 저장한 속성(Property)
				
				var result=xhr.responseText;	
				document.getElementById("display").innerHTML=result;
			} else {//요청에 대한 비정상적인 응답을 받은 경우 - 응답코드 : 4XX or 5XX
				alert("에러코드 = "+xhr.status);
			}
		} else {
			document.getElementById("display").innerHTML="<img src='images/loading.gif' width='50'>";
		}
	}
	</script>
</body>
</html>







