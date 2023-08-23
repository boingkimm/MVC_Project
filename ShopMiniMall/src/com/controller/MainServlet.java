package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.GoodsDTO;
import com.service.GoodsService;
import com.service.GoodsServiceImpl;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//main에서 보여줄 상품목록
		String gCategory=request.getParameter("gCategory");
		//맨 처음 요청시
		if (gCategory==null) {
			gCategory = "bottom";
		}
		GoodsService service = new GoodsServiceImpl();
		List<GoodsDTO> list = service.goodsList(gCategory);
		
		//scope 저정
		request.setAttribute("goodsList", list);
		
		//요청 위임
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}
