<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- welcome_two.jsp 문서를 AJAX 기능으로 요청하여 처리결과(HTML)를 응답받아 엘리먼트를 변경하여
클라이언트에게 전달하는 JSP 문서 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>jQuery AJAX</h1>
	<hr>
	<label for="name">이름 입력</label>
	<input type="text" id="name">
	<button type="button" id="btn">전송</button>
	<hr>
	<div id="message"></div>
	
	<script type="text/javascript">
	$("#btn").click(function() {
		var name=$("#name").val();
		if(name=="") {
			$("#message").html("이름을 입력해 주세요.");
			return;
		}
		
		$("#name").val("");//입력태그 초기화
		
		//$.ajax(object) : AJAX 기능을 제공하는 메소드
		// => AJAX 기능을 구현하기 위한 정보를 메소드의 매개변수에 Object 객체로 전달
		// => 매개변수에 전달되는 Object 객체에는 정해진 요소에 값 또는 함수를 설정하여 AJAX 구현  
		$.ajax({
			type: "post",//요청방식 설정 
			url: "welcome_two.jsp",//요청 웹프로그램의 URL 주소 설정	
			data: "name="+name,//전달될 이름과 값 설정
			dataType: "html",//요청에 대한 응답결과 형식
			//정상적인 응답결과를 제공받아 처리하는 함수 등록
			// => 등록된 함수의 매개변수에는 응답결과가 자동으로 전달되어 저장
			success: function(result) {
				$("#message").html(result);
			}, 
			//비정상적인 응답결과를 제공받아 처리하는 함수 등록
			// => 등록된 함수의 매개변수에는 XMLHttpReuest 객체가 자동으로 전달되어 저장
			error: function(xhr) {
				$("#message").html("에러코드 = "+xhr.status);
			}
		});
	});
	</script>
</body>
</html>









