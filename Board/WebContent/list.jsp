<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록보기</title>
</head>
<body>
<h2>게시판 목록보기</h2>
<c:set var="boardList" value="${boardList}"/>
<table border="1">
  <tr>
    <th>글번호</th>
    <th>제목</th>
    <th>작성자</th>
    <th>작성일</th>
    <th>조회수</th>
    <th>삭제</th>
  </tr>
<c:forEach var="dto" items="${boardList}">
  <tr>
    <td>${dto.num}</td>
    <td>${dto.title}</td>
    <td>${dto.author}</td>
    <td>${dto.writeday}</td>
    <td>${dto.readcnt}</td>
    <td><button data-num="${dto.num}">삭제</button></td>
  </tr>
</c:forEach>
</table>
<a href="writeui">글쓰기</a>
</body>
</html>