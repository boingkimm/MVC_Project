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
import com.dto.OrderDTO;
import com.service.OrderService;
import com.service.OrderServiceImpl;

@WebServlet("/OrderDoneServlet")
public class OrderDoneServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//로그인 여부 확인 필수
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
				
		String nextPage = null;
		if (dto!=null) {
			//로그인한 경우
			
			/* OrderConfirmServlet에서 결제하기 클릭시 
			 * ?same_address=on&
			 * orderName=홍길동&
			 * post=13529&
			 * addr1=경기~&
			 * addr2=경기~&
			 * phone=01012345678&
			 * payMethod=신용카드
			 * => input태그의 name값 넘어옴
			 * => 나머지 데이터 얻어오기 : 히든태그
			 */
			
			//주문자 정보 (로그인 정보)
			//session에서 userid 얻어오기
			String userid = dto.getUserid();
			
			//상품 정보
			String gCode = request.getParameter("gCode");
			String gName = request.getParameter("gName");
			String gPrice = request.getParameter("gPrice");
			String gSize = request.getParameter("gSize");
			String gColor = request.getParameter("gColor");
			String gAmount = request.getParameter("gAmount");
			String gImage = request.getParameter("gImage");
			
			//배송 정보
			String orderName = request.getParameter("orderName");
			String post = request.getParameter("post");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String phone = request.getParameter("phone");
			String payMethod = request.getParameter("payMethod");
			
			//Cart 테이블에서 삭제할 num
			String del_num = request.getParameter("num");
			
			//OrderDTO에 모두 저장
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setUserid(userid);
			orderDTO.setgCode(gCode);
			orderDTO.setgName(gName);
			orderDTO.setgPrice(Integer.parseInt(gPrice));
			orderDTO.setgSize(gSize);
			orderDTO.setgColor(gColor);
			orderDTO.setgAmount(Integer.parseInt(gAmount));
			orderDTO.setgImage(gImage);
			 
			orderDTO.setOrderName(orderName);
			orderDTO.setPost(post);
			orderDTO.setAddr1(addr1);
			orderDTO.setAddr2(addr2);
			orderDTO.setPhone(phone);
			orderDTO.setPayMethod(payMethod);
			
			//orderDTO 값을 서비스 거쳐서 DAO에 전달
			OrderService service = new OrderServiceImpl();
			int n = service.orderDone(orderDTO, Integer.parseInt(del_num));
			//scope에 저장
			request.setAttribute("orderDTO", orderDTO);
			
			//요청위임
			nextPage = "orderDone.jsp";
		} else {
			//로그인 안했거나 타임아웃
			nextPage = "member/checkLogin.jsp";
		}

		request.getRequestDispatcher(nextPage).forward(request, response);
	
	}

}
