package com.controller;

import java.io.IOException;
import java.util.HashMap;

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

@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 여부 확인 필수 (로그인한 상태여야 로그아웃 가능)
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		if (dto!=null) {
			//로그인한 경우
			//ajax에서 보낸 data
			String num = request.getParameter("num");
			String gAmount = request.getParameter("gAmount");
			
			//hashmap에 담기
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("num", Integer.parseInt(num));
			map.put("gAmount", Integer.parseInt(gAmount));
			
			//map을 서비스 거쳐서 DAO에 전달
			CartService service = new CartServiceImpl();
			int n = service.cartUpdate(map);
			//ajax 이기 때문에 화면처리 불필요 (응답처리 필요X) => ajax의 success로 가는 것임
			
		} else {
			//로그인 안했거나 타임아웃
			response.sendRedirect("member/checkLogin.jsp");
		}
		
	}

}

