<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 키워드를 입력받아 키워드 포함된 제시어를 클라이언트에게 전달하는 JSP 문서 --%>
<%-- => 키워드를 입력할 경우 키워드가 포함된 제시어 관련 정보를 suggest_two.jsp 문서를 AJAX 
기능으로 요청하여 XML 텍스트 데이타로 응답받아 출력되도록 처리 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
<style type="text/css">
#keyword {
	position: absolute;
	top: 100px;
	left: 10px;
	width: 300px;
	font-size: 16px;	
}

#suggestDiv {
	position: absolute;
	top: 125px;
	left: 10px;
	width: 300px;
	border: 1px solid black;
	cursor: pointer;
	font-size: 16px;
	z-index: 999;
	background: rgba(255,255,255,1); 
	display: none;
}

#choice {
	position: absolute;
	top: 150px;
	left: 10px;
}
</style>
</head>
<body>
	<h1>제시어 기능</h1>
	<hr>
	<div>
		<%-- 키워드를 입력받기 위한 태그 --%>
		<input type="text" id="keyword">
		
		<%-- 제시어를 제공받아 출력하기 위한 태그 --%>
		<div id="suggestDiv">
			<div id="suggestList"></div>
		</div>
		
		<%-- 선택된 제시어 관련 정보를 출력하기 위한 태그 --%>
		<div id="choice"></div>
	</div>	
	
	<script type="text/javascript">
	document.getElementById("keyword").focus();
	
	document.getElementById("keyword").onkeyup=function() {
		var keyword=document.getElementById("keyword").value;
		if(keyword=="") {
			document.getElementById("suggestDiv").style.display="none";
			return;
		}
		
		new xyz.itwill.Ajax("POST", "suggest_two.jsp", "keyword="+keyword, displaySuggest);
	}
	
	function displaySuggest(xhr) {
		if(xhr.readyState==4) {
			if(xhr.status==200) {
				var xmlDoc=xhr.responseXML;
				
				var code=xmlDoc.getElementsByTagName("code").item(0).firstChild.nodeValue;
				
				if(code=="success") {
					var data=xmlDoc.getElementsByTagName("data").item(0).firstChild.nodeValue;
					
					var suggestArray=JSON.parse(data);
					
					var html="";
					for(i=0;i<suggestArray.length;i++) {
						html+="<a href=\"javascript:selectSuggest('"+suggestArray[i].word
							+"','"+suggestArray[i].url+"');\">"+suggestArray[i].word+"</a><br>";
					}
										
					document.getElementById("suggestList").innerHTML=html;
					
					document.getElementById("suggestDiv").style.display="block";
				} else {
					document.getElementById("suggestDiv").style.display="none";
				}
			} else {
				alert("에러코드 = "+xhr.status);
			}
		}
	}
	
	function selectSuggest(word, url) {
		//alert("word = "+word+", url = "+url);
		
		document.getElementById("keyword").value=word;
		document.getElementById("choice").innerHTML="<a href="+url+">"+word+"</a>";
		
		document.getElementById("suggestDiv").style.display="none";
	}
	</script>
</body>
</html>











