package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.CartServiceImpl;

@WebServlet("/CartListServlet")
public class CartListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 여부 확인 필수
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		
		String nextPage = null;
		if (dto!=null) {
			//로그인한 경우
			String userid = dto.getUserid(); //로그인한 계정의 장바구니만 보여주기
			
			//userid값을 서비스 거쳐서 DAO 전달하고 반환
			CartService service = new CartServiceImpl();
			List<CartDTO> list = service.cartList(userid);
			
			//scope에 저장
			request.setAttribute("cartList", list);
			
			nextPage = "cartList.jsp";
		} else {
			//로그인 안했거나 타임아웃
			nextPage = "member/checkLogin.jsp";
		}

		//요청위임
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}