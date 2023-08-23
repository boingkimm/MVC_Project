package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 여부 확인 필수
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String nextPage = null;
		if (dto!=null) {
			//로그인한 경우
			nextPage="mypage.jsp";
			String userid = dto.getUserid();
			//userid를 서비스 거쳐서 DAO전달 (MemberDTO에 userid가 있지만 다시 땡겨오기)
			MemberService service = new MemberServiceImpl();
			MemberDTO mypage = service.mypage(userid);
			session.setAttribute("login", mypage);
		} else {
			//로그인 안했거나 타임아웃
			nextPage="member/checkLogin.jsp";
		}
		
		//요청위임
		response.sendRedirect(nextPage);
	}

}
