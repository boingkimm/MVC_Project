<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.BoardDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 자세히 보기</title>
</head>
<body>
<h2>게시판 자세히 보기</h2>
<form action="update" method="get">
<input type="hidden" name="num" value="${boardRetrieve.num}"><br>
글번호:${boardRetrieve.num}<br>
작성일:${boardRetrieve.writeday}<br>
조회수:${boardRetrieve.readcnt}<br>
제목: <input type="text" name="title" value="${boardRetrieve.title}"><br>
작성자: <input type="text" name="author" value="${boardRetrieve.author}"><br>
내용: <br><textarea rows="10" cols="50" name="content">${boardRetrieve.content}</textarea><br>
<input type="submit" value="수정">
</form>
<a href="list">목록</a>
</body>
</html>
