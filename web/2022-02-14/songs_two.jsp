<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 음원 목록을 JSON 형식의 텍스트 데이타로 응답하는 JSP 문서 --%>    
<%
	String now=new SimpleDateFormat("yyyy년 MM월 dd일 HH시").format(new Date());
%>
{"now":"<%=now%>","songs":[{"title":"사랑은 늘 도망가","singer":"임영웅"}
,{"title":"취중고백","singer":"김민석(멜로망스)"},{"title":"ELEVEN","singer":"IVE(아이브)"}
,{"title":"Step Back","singer":"GOT the beat"},{"title":"호랑수월가","singer":"탑현"}]}