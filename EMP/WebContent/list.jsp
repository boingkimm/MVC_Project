<%@page import="java.util.List"%>
<%@page import="com.dto.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원관리</title>
</head>
<body>
	<h4>[ 사원 목록 ]</h4>
	<hr>
	<%
		List<EmpDTO> list = (List<EmpDTO>) request.getAttribute("empList");
	%>
	<table border="1">
		<!-- 검색화면 -->
		<tr>
			<td colspan="5">
				<form action="list" method="post">
					<select name="searchName">
						<option value="hireDate">입사일(년도만)</option>
						<option value="ename">이름</option>
					</select>
					<input type="text" name="searchValue">
					<input type="submit" value="검색">
				</form>
			</td>
		</tr>
		<!-- 검색화면 끝 -->
		<!-- 정렬기준 -->
		<tr>
			<th>정렬기준</th>
			<td colspan="4">월급 높은 순: <input type="radio" name="orderby"
				value="desc"> 월급 낮은 순: <input type="radio" name="orderby"
				value="asc"> <input type="submit" value="정렬">
			</td>
		</tr>
		<!-- 정렬기준 끝 -->
		<tr>
			<th>사원번호</th>
			<th>사원이름</th>
			<th>직업</th>
			<th>입사일</th>
			<th>월급</th>
		</tr>
		<%
			for (EmpDTO dto : list) {
			int empno = dto.getEmpno();
			String ename = dto.getEname();
			String job = dto.getJob();
			String hireDate = dto.getHireDate();
			int sal = dto.getSal();
		%>
		<tr>
			<td><%=empno%></td>
			<td><%=ename%></td>
			<td><%=job%></td>
			<td><%=hireDate%></td>
			<td><%=sal%></td>
		</tr>
		<%
			} //end for
		%>
	</table>
	<a href="writeform">사원등록</a>

</body>
</html>
