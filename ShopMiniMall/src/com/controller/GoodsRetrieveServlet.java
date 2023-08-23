package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.GoodsService;
import com.service.GoodsServiceImpl;
import com.service.MemberService;
import com.service.MemberServiceImpl;

@WebServlet("/GoodsRetrieveServlet")
public class GoodsRetrieveServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String gCode = request.getParameter("gCode");
		
		//gCode값을 서비스 거치고 DAO까지 전달하고 반환
		GoodsService service = new GoodsServiceImpl();
		GoodsDTO dto = service.goodsRetrieve(gCode);
		
		//scope에 저장
		request.setAttribute("goodsRetrieve", dto);
		
		//요청위임
		request.getRequestDispatcher("goodsRetrieve.jsp").forward(request, response);

	}

}
