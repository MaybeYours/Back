<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 제품번호를 전달받아 PRODUCT 테이블에 저장된 해당 제품번호의 제품정보를 검색하여 
입력태그의 초기값으로 설정하고 변경값을 입력하는 JSP 문서 --%>    
<%-- => 관리자만 JSP 문서를 요청하여 처리되도록 권한 설정 --%>
<%-- => [제품변경]을 클릭한 경우 제품정보 변경페이지(product_modify_action.jsp)로 이동 - 입력값(제품정보) 전달 --%>    
<%@include file="/site/security/admin_check.jspf"%>     
<%
	//비정상적인 요청에 대한 응답 처리
	if(request.getParameter("num")==null) {
		out.println("<script type='text/javascript'>");
		out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=error&work=error400';");
		out.println("</script>");
		return;	
	}

	//전달값을 반환받아 저장
	int num=Integer.parseInt(request.getParameter("num"));
	
	//제품번호를 전달받아 PRODUCT 테이블에 저장된 해당 제품번호의 제품정보를 검색하여
	//반환하는 DAO 클래스의 메소드 호출
	ProductDTO product=ProductDAO.getDAO().selectNumProduct(num);
%>
<style type="text/css">
#product {
	width: 800px;
	margin: 0 auto;
}

table {
	margin: 0 auto;
}

td {
	text-align: left;
}
</style>
     
<div id="product">
	<h2>제품변경</h2>
	
	<%-- 파일을 입력받아 전달하기 위해 form 태그의 method 속성값을 [post]로 설정하고 
	enctype 속성값을 [multipart/form-data]로 설정 --%>
	<form action="<%=request.getContextPath()%>/site/index.jsp?workgroup=admin&work=product_modify_action"
		method="post" enctype="multipart/form-data" id="productForm">
	<input type="hidden" name="num" value="<%=product.getNum()%>">
	<%-- 제품이미지를 변경하지 않을 경우 기존 제품이미지를 사용하기 위해 전달하거나
	제품이미지를 변경할 경우 기존 제품이미지를 서버 디렉토리에서 삭제하기 위해 전달 --%>
	<input type="hidden" name="currentImage" value="<%=product.getImage()%>">	
	<table>
		<tr>
			<td>카테고리</td>
			<td>
				<select name="category">
					<option value="CPU" <% if(product.getCategory().equals("CPU")) { %> selected="selected" <% } %>>중앙처리장치(CPU)</option>
					<option value="MAINBOARD" <% if(product.getCategory().equals("MAINBOARD")) { %> selected="selected" <% } %>>메인보드(MAINBOARD)</option>
					<option value="MEMORY" <% if(product.getCategory().equals("MEMORY")) { %> selected="selected" <% } %>>메모리(MEMORY)</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>제품명</td>
			<td><input type="text" name="name" id="name" value="<%=product.getName()%>"></td>
		</tr>
		<tr>
			<td>제품이미지</td>
			<td>
				<img src="<%=request.getContextPath()%>/site/product_image/<%=product.getImage() %>" width="200">
				<br>
				<span style="color: red;">이미지를 변경하지 않을 경우 입력하지 마세요.</span>
				<input type="file" name="image" id="image">
			</td>
		</tr>
		<tr>
			<td>상세정보</td>
			<td><textarea rows="7" cols="60" name="detail" id="detail"><%=product.getDetail() %></textarea></td>
		</tr>
		<tr>
			<td>제품수량</td>
			<td><input type="text" name="qty" id="qty" value="<%=product.getQty()%>"></td>
		</tr>
		<tr>
			<td>제품가격</td>
			<td><input type="text" name="price" id="price" value="<%=product.getPrice()%>"></td>
		</tr>
		<tr>
			<td colspan="2"><button type="submit">제품변경</button></td>
		</tr>
	</table>		
	</form>
	
	<div id="message" style="color: red;"></div>	
</div>

<script>
$("#productForm").submit(function() {
	if($("#name").val()=="") {
		$("#message").text("제품명을 입력해 주세요.");
		$("#name").focus();
		return false;
	}
	
	if($("#detail").val()=="") {
		$("#message").text("상세정보를 입력해 주세요.");
		$("#detail").focus();
		return false;
	}
	
	if($("#qty").val()=="") {
		$("#message").text("제품수량을 입력해 주세요.");
		$("#qty").focus();
		return false;
	}
	
	if($("#price").val()=="") {
		$("#message").text("제품가격을 입력해 주세요.");
		$("#price").focus();
		return false;
	}
});
</script>