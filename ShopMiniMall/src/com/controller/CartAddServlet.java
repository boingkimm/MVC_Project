package com.controller;

import java.io.IOException;

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

@WebServlet("/CartAddServlet")
public class CartAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 상품 상세페이지에서 장바구니 클릭 시 - 7개 값이 넘어옴 (히든태그 포함)
		 *http://localhost:8090/ShopMiniMall/CartAddServlet?
		 *gImage=top6&
		 *gCode=T6&
		 *gName=클로에+슬리프+러플+크롭+탑
		 *gPrice=9800&
		 *gSize=M&
		 *gColor=black&
		 *gAmount=1 
		*/
		
		//로그인 여부 확인
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String nextPage = null;
		if (dto!=null) {
			//로그인한 경우
			String userid = dto.getUserid();
			String gCode = request.getParameter("gCode");
			String gName = request.getParameter("gName");
			String gPrice = request.getParameter("gPrice");
			String gSize = request.getParameter("gSize");
			String gColor = request.getParameter("gColor");
			String gAmount = request.getParameter("gAmount");
			String gImage = request.getParameter("gImage");
			
			//userid까지 포함해서 넘기기
			CartDTO cartDTO = new CartDTO();
			cartDTO.setUserid(userid);
			cartDTO.setgCode(gCode);
			cartDTO.setgName(gName);
			cartDTO.setgPrice(Integer.parseInt(gPrice));
			cartDTO.setgSize(gSize);
			cartDTO.setgColor(gColor);
			cartDTO.setgAmount(Integer.parseInt(gAmount));
			cartDTO.setgImage(gImage);
			
			//cartDTO값을 서비스 거쳐서 DAO로 전달
			CartService service = new CartServiceImpl();
			int n = service.cartAdd(cartDTO);  //dto 아님. (MemberDTO dto 있어서 CartDTO cartDTO로 생성함)

			nextPage = "goods/cartAddSuccess.jsp";
			
		} else {
			//로그인 안했거나 타임아웃
			nextPage = "member/checkLogin.jsp";
		}

		//요청위임
		response.sendRedirect(nextPage);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}