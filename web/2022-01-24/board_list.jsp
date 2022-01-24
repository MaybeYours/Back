<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@page import="xyz.itwill.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- BOARD 테이블에 저장된 게시글을 검색하여 게시글 목록을 클라이언트에게 전달하는 JSP 문서 --%>
<%-- => 게시글 목록을 페이지로 구분하여 응답 - 페이징 처리 --%>
<%-- => 로그인 사용자가 [글쓰기]를 클릭한 경우 게시글 입력페이지(board_write.jsp)로 이동 --%>
<%-- => 게시글의 [제목]을 클릭한 경우 게시글 출력페이지(board_detail.jsp)로 이동 - 글번호,페이지 번호,검색 관련 정보 전달 --%>
<%-- => [페이지 번호] 또는 [검색]을 클릭한 경우 게시글 목록 출력페이지(board_list.jsp)로 이동 - 페이지 번호, 검색 관련 정보 전달 --%>
<%
	//검색 관련 전달값을 반환받아 저장
	String search=request.getParameter("search");
	if(search==null) {//전달값이 없는 경우
		search="";
	}
	
	String keyword=request.getParameter("keyword");
	if(keyword==null) {
		keyword="";
	}
	//System.out.println(search+" = "+keyword);

	//JSP 문서를 요청하여 전달된 페이지 번호를 반환받아 저장
	// => 페이지 번호 전달값이 없는 첫번째 페이지의 게시글 목록 응답
	int pageNum=1;
	if(request.getParameter("pageNum")!=null) {//전달값이 있는 경우
		pageNum=Integer.parseInt(request.getParameter("pageNum"));
	}
	
	//하나의 페이지에 검색될 게시글의 갯수 설정 - 전달값 이용 가능 
	int pageSize=10;
	
	//BOARD 테이블에 저장된 전체 게시글의 갯수를 검색하여 반환하는 DAO 클래스의 메소드 호출
	// => 검색 기능 미구현
	//int totalBoard=BoardDAO.getDAO().selectBoardCount();
	
	//BOARD 테이블에 저장된 게시글 중 검색어 포함된 게시글의 갯수를 검색하여 반환하는 DAO 클래스의 메소드 호출
	// => 검색 기능 구현
	int totalBoard=BoardDAO.getDAO().selectBoardCount(search, keyword);
	
	//전체 페이지의 갯수를 계산하여 저장
	//int totalPage=totalBoard/pageSize+totalBoard%pageSize==0?0:1;
	int totalPage=(int)Math.ceil((double)totalBoard/pageSize);

	//요청 페이지 번호에 대한 검증
	if(pageNum<=0 || pageNum>totalPage) {//전달된 페이지 번호가 비정상적인 경우
		pageNum=1;
	}
	
	//요청 페이지 번호에 대한 게시글 시작 행번호를 계산하여 저장
	//ex) 1Page : 1, 2Page : 11, 3Page : 21, 4Page : 31, ...
	int startRow=(pageNum-1)*pageSize+1;
	
	//요청 페이지 번호에 대한 게시글 종료 행번호를 계산하여 저장
	//ex) 1Page : 10, 2Page : 20, 3Page : 30, 4Page : 40, ...
	int endRow=pageNum*pageSize;

	//마지막 페이지에 대한 게시글 종료 행번호를 전달 게시글의 갯수로 변경
	if(endRow>totalBoard) {
		endRow=totalBoard;
	}
	
	//요청 페이지에 대한 게시글 시작 행번호와 게시글 종료 행번호를 전달받아 BOARD 테이블에 저장된
	//해당 행범위의 게시글을 검색하여 반환하는 DAO 클래스의 메소드 호출
	//List<BoardDTO> boardList=BoardDAO.getDAO().selectBoardList(startRow, endRow);
	List<BoardDTO> boardList=BoardDAO.getDAO().selectBoardList(startRow, endRow, search, keyword);
	
	//세션에 저장된 권한 관련 정보(회원정보 - MemberDTO)를 반환받아 저장
	// => 로그인 사용자에게만 글쓰기 권한 제공
	// => 비밀 게시글인 경우 로그인 사용자가 게시글 작성자 또는 관리자인 경우에만 접근 권한 제공
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
	
	//요청 페이지에 출력될 게시글에 대한 글번호 시작값을 계산하여 저장
	//ex) 검색 게시글의 갯수 : 100 >> 1Page : 100~91, 2Page : 90~81, 3Page : 80~71,
	int number=totalBoard-(pageNum-1)*pageSize;
	
	//시스템의 현재 날짜를 반환받아 저장
	// => 게시글 작성일을 현재 날짜와 비교하여 구분 출력
	String currentDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<style type="text/css">
#board_list {
	width: 1000px;
	margin: 0 auto;
	text-align: center;
}

#board_title {
	font-size: 1.2em;
	font-weight: bold;
}

#btn {
	text-align: right;
}

table {
	margin: 5px auto;
	border: 1px solid black;
	border-collapse: collapse;
}

th {
	border: 1px solid black;
	background-color: black;
	color: white;
}

td {
	border: 1px solid black;
	text-align: center;
}

.subject {
	text-align: left;
	padding: 5px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

#board_list a:hover {
	text-decoration: none;
	color: blue;
}

.secret, .remove {
	background-color: black;
	color: white;
	font-size: 14px;
	border: 1px solid black;
	border-radius: 4px;
}
</style>

<div id="board_list">
	<div id="board_title">게시글 목록(게시글 갯수 : <%=totalBoard %>)</div>

	<% if(loginMember!=null) {//로그인 사용자인 경우 %>	
	<div id="btn">
		<button type="button" onclick="location.href='<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_write';">글쓰기</button>
	</div>
	<% } %>
	
	<table>
		<tr>
			<th width="100">번호</th>
			<th width="500">제목</th>
			<th width="100">작성자</th>
			<th width="100">조회수</th>
			<th width="200">작성일</th>
		</tr>
		
		<% if(totalBoard==0) { %>
		<tr>
			<td colspan="5">검색된 게시글이 하나도 없습니다.</td>
		</tr>
		<% } else { %>
			<%-- 검색된 게시글 목록에서 게시글을 하나씩 제공받아 응답 - 반복 --%>
			<% for(BoardDTO board:boardList) { %>
				<tr>
					<%-- 글번호 : BOARD 테이블에 저장된 글번호가 아닌 계산된 글번호 출력 --%>
					<td><%=number %></td>
					
					<%-- 제목 --%>
					<td class="subject">
						<% if(board.getReStep()!=0) {//답글인 경우 %>
							<%-- 답글의 깊이에 따라 왼쪽 여백을 다르게 설정하여 출력 --%>
							<span style="margin-left: <%=board.getReLevel()*20%>px;">└[답글]</span>
						<% } %>
						
						<%-- 게시글 상태를 구분하여 제목과 링크를 다르게 설정 --%>
						<% if(board.getStatus()==1) {//일반 게시글인 경우 %>
							<a href="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_detail&num=<%=board.getNum() %>&pageNum=<%=pageNum %>&search=<%=search%>&keyword=<%=keyword%>"><%=board.getSubject() %></a>
						<% } else if(board.getStatus()==2) {//비밀 게시글인 경우 %>
							<span class="secret">비밀글</span>
							<%-- 로그인 사용자 중 게시글 작성자이거나 관리자인 경우 - 접근 권한이 있는 경우 --%>
							<% if(loginMember!=null && (loginMember.getId().equals(board.getId())
									|| loginMember.getStatus()==9)) { %>
								<a href="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_detail&num=<%=board.getNum() %>&pageNum=<%=pageNum %>&search=<%=search%>&keyword=<%=keyword%>"><%=board.getSubject() %></a>
							<% } else { %>
								작성자 또는 관리자만 확인 가능합니다.
							<% } %>
						<% } else if(board.getStatus()==0) {//삭제 게시글인 경우 %>
							<span class="remove">삭제글</span>
							작성자 또는 관리자에 의해 삭제된 게시글입니다.
						<% } %>
					</td>
					
					<% if(board.getStatus()!=0) {//삭제 게시글이 아닌 경우 %>
						<%-- 작성자 --%>
						<td><%=board.getWriter() %></td>
						
						<%-- 조회수 --%>
						<td><%=board.getReadcount() %></td>
						
						<%-- 작성일 : 오늘 날짜에 작성된 게시글은 시간만 출력하고 오늘 날짜가  
						아닌 날짜에 작성된 게시글은 날짜와 시간 출력  --%>
						<td>
							<%-- 게시글 작성날짜와 시스템 날짜가 같은 경우 --%>
							<% if(currentDate.equals(board.getRegDate().substring(0, 10))) { %>
								<%=board.getRegDate().substring(11) %>
							<% } else { //게시글 작성날짜와 시스템 날짜가 다른 경우 %>
								<%=board.getRegDate() %>
							<% } %>
						</td>
					<% } else {//삭제 게시글인 경우 %>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					<% } %>
				</tr>
				
				<%-- 출력 글번호의 변수값 1 감소 --%>
				<% number--; %>	
			<% } %>
		<% } %>
	</table>
	
	<%-- 페이지 번호 출력 및 링크 설정 - 블럭화 처리 --%>
	<%
		//페이지 블럭에 출력될 페이지의 갯수 설정
		int blockSize=5;
	
		//페이지 블럭에 출력될 시작 페이지 번호를 계산하여 저장
		//ex) 1Block : 1, 2Block : 6, 3Block : 11, 4Block : 16,...
		int startPage=(pageNum-1)/blockSize*blockSize+1; 

		//페이지 블럭에 출력될 종료 페이지 번호를 계산하여 저장
		//ex) 1Block : 5, 2Block : 10, 3Block : 15, 4Block : 20,...
		int endPage=startPage+blockSize-1;
		
		//마지막 페이지 불럭의 종료 페이지 번호 변경
		if(endPage>totalPage) {
			endPage=totalPage;
		}
	%>
	
	<% if(startPage>blockSize) {//시작 페이지 번호가 blockSize 변수값을 초과한 경우 링크 설정 %>
		<a href="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_list&pageNum=1&search=<%=search%>&keyword=<%=keyword%>">[처음]</a>
		<a href="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_list&pageNum=<%=startPage-blockSize%>&search=<%=search%>&keyword=<%=keyword%>">[이전]</a>
	<% } else {//시작 페이지 번호가 blockSize 변수값을 초과하지 않을 경우 링크 미설정 %>
		[처음][이전]
	<% } %>
	
	<% for(int i=startPage;i<=endPage;i++) { %>
		<% if(pageNum!=i) {//요청 페이지 번호와 출력 페이지 번호가 같지 않은 경우 링크 설정 %>
			<a href="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_list&pageNum=<%=i%>&search=<%=search%>&keyword=<%=keyword%>">[<%=i %>]</a>
		<% } else {//요청 페이지 번호와 출력 페이지 번호가 같을 경우 링크 미설정 %>
			[<%=i %>]
		<% } %>
	<% } %>
	
	<% if(endPage!=totalPage) {//종료 페이지 번호와 전체 페이지 갯수가 같지 않은 경우 링크 설정 %>
		<a href="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_list&pageNum=<%=startPage+blockSize%>&search=<%=search%>&keyword=<%=keyword%>">[다음]</a>
		<a href="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_list&pageNum=<%=totalPage%>&search=<%=search%>&keyword=<%=keyword%>">[마지막]</a>
	<% } else {//종료 페이지 번호와 전체 페이지 갯수가 같은 경우 링크 미설정 %>
		[다음][마지막]
	<% } %>
	
	<%-- 게시글 검색 기능 제공 --%>
	<form action="<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_list" method="post">
		<%-- select 태그에 의해 입력되어 전달되는 값은 비교 컬럼명과 동일하게 설정 --%>
		<select name="search">
			<option value="writer" selected="selected">&nbsp;작성자&nbsp;</option>
			<option value="subject">&nbsp;제목&nbsp;</option>
			<option value="content">&nbsp;내용&nbsp;</option>
		</select>
		<input type="text" name="keyword">
		<button type="submit">검색</button>
	</form>
</div>













