<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.MemberDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- top.jsp -->
<!-- session scope에 login key에 해당하는 값이 있는지에 따라 로그인 여부 확인됨-->
<!-- 
login에 해당하는 키값을
request에서 먼저 찾고 없으면 session, application 순서로 찾음
명시하려면 session.login

${login!=null} = ${empty login}
-->

<c:if test="${empty login}">
	<a href="<c:url value='LoginUIServlet'/>">로그인</a>
	<a href="<c:url value='MemberUIServlet'/>">회원가입</a>
</c:if>

<c:if test="${!empty login}">
	안녕하세요. ${login.username}님<br>
	<a href="">로그아웃</a>
	<a href="">mypage</a>
	<a href="">장바구니목록</a>
</c:if>