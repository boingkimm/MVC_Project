package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.EmpBiz;
import com.biz.EmpBizImpl;
import com.dto.EmpDTO;

@WebServlet("/write")
public class EmpWriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		String hireDate = request.getParameter("hireDate");
		String sal = request.getParameter("sal");
				
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(Integer.parseInt(empno));
		dto.setEname(ename);
		dto.setJob(job);
		dto.setHireDate(hireDate);
		dto.setSal(Integer.parseInt(sal));
		
		EmpBiz biz = new EmpBizImpl();
		int n = biz.write(dto);
		
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
