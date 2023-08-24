package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.OrderService;
import com.service.OrderServiceImpl;

@WebServlet("/OrderConfirmServlet")
public class OrderConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//로그인 여부 확인 필수
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
				
		String nextPage = null;
		if (dto!=null) {
			//로그인한 경우
			OrderService service = new OrderServiceImpl();
			
			//주문정보
			String num = request.getParameter("num");
			//num값에 해당하는 CartDTO 얻기
			CartDTO cDTO = service.cartByNum(Integer.parseInt(num));
			
			//고객정보
			String userid = dto.getUserid();
			//userid값에 해당하는 MemberDTO 얻기
			MemberDTO mDTO = service.memberByUserid(userid);
			
			//scope에 저장
			request.setAttribute("cDTO", cDTO);
			request.setAttribute("mDTO", mDTO);
			
			//요청위임
			nextPage = "orderConfirm.jsp";
		} else {
			//로그인 안했거나 타임아웃
			nextPage = "member/checkLogin.jsp";
		}

		request.getRequestDispatcher(nextPage).forward(request, response);
	
	}

}
