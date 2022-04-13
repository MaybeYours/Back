<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- songs_two.jsp 문서를  AJAX 기능으로 요청하여 처리결과(JSON)를 응답받아 엘리먼트를 변경하여
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
	<h2>음원챠트(<span id="now"></span> 현재 기준)</h2>
	<div id="songList"></div>
	
	<script type="text/javascript">
	$.ajax({
		type: "get",
		url: "songs_two.jsp",
		dataType: "json",
		success: function(obj) {
			//alert(obj);//[object Object]
			
			$("#now").html(obj.now);
			
			var html="<ol>";
			$(obj.songs).each(function() {
				html+="<li><b>"+this.title+"</b> - "+this.singer+"</li>";
			});
			html+="</ol>";
			
			$("#songList").html(html);
		},
		error: function(xhr) {
			alert("에러코드 = "+xhr.status);	
		}
	});
	</script>
</body>
</html>











