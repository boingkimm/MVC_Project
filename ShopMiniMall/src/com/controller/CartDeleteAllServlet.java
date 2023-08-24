package com.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CartDAO;
import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.CartServiceImpl;

import sun.print.resources.serviceui;

@WebServlet("/CartDeleteAllServlet")
public class CartDeleteAllServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 여부 확인 필수
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		
		String nextPage = null;
		if (dto!=null) {
			//로그인한 경우
			String [] check = request.getParameterValues("check"); //배열로 저장됨
			List<String> del_list = Arrays.asList(check);  //배열을 리스트로 변경
			
			//del_list를 서비스 거쳐서 DAO까지 전달
			CartService service = new CartServiceImpl();
			int n = service.cartDeleteAll(del_list);
			nextPage = "CartListServlet";
		} else {
			//로그인 안했거나 타임아웃
			nextPage = "member/checkLogin.jsp";
		}
		response.sendRedirect(nextPage);
		//request.getRequestDispatcher(nextPage).forward(request, response);  //실습 url확인용
		/*
		 * ?check=22&
		 * check=21&
		 * check=24&
		 */
	}

}

