package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.EmpBizImpl;
import com.biz.Empbiz;
import com.dto.EmpDTO;

@WebServlet("/list")
public class EmpListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Biz연동
		Empbiz biz = new EmpBizImpl();
		List<EmpDTO> list = biz.list();
		
		//scope저장
		request.setAttribute("empList", list);
		//위임
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} 
