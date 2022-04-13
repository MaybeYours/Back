<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/log.js"></script>
</head>
<body>
	<h1>회원목록</h1>
	<hr>
	<div id="log"></div>
	
	<script type="text/javascript">
	new xyz.itwill.Ajax("GET", "member_xmljson_two.jsp", null, function(xhr) {
		if(xhr.readyState==4) {
			if(xhr.status==200) {
				var xmlDoc=xhr.responseXML;
				
				var code=xmlDoc.getElementsByTagName("code").item(0).firstChild.nodeValue;
				
				if(code=="success") {
					var memberList=JSON.parse(xmlDoc.getElementsByTagName("data").item(0).firstChild.nodeValue);
					
					for(i=0;i<memberList.length;i++) {
						var id=memberList[i].id;
						var name=memberList[i].name;
						log("아이디 = "+id+", 이름 = "+name);
					}
				} else {
					log("검색된 회원정보가 존재하지 않습니다.");
				}
			} else {
				log("에러코드 = "+xhr.status);
			}
		}
	});
	</script>
</body>
</html>







