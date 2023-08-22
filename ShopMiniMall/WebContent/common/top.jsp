<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- top.jsp -->
<!-- session scope에 login key에 해당하는 dto값이 있는지 여부에 따라 로그인 여부 -->
<%
	MemberDTO dto = (MemberDTO)session.getAttribute("login");

	if(dto==null){
%>
<a href="LoginUIServlet">로그인</a>
<a href="MemberUIServlet">회원가입</a>
<%
	}else{
%>
안녕하세요. <%= dto.getUsername() %>님
<a href="">로그아웃</a>
<a href="">mypage</a>
<a href="">장바구니목록</a>
<%
	}
%>