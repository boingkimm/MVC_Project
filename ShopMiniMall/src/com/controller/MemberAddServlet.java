package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

@WebServlet("/MemberAddServlet")
public class MemberAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//memberform.jsp에서 모두 넘어오기
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String username = request.getParameter("username");
		String post = request.getParameter("post");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		
		//MemberDTO에 모으기
		MemberDTO dto = new MemberDTO(userid, passwd, username, post, addr1, addr2, phone1, phone2, phone3, email1, email2);
		
		//dto를 서비스 거쳐서 dao까지 전달
		MemberService service = new MemberServiceImpl();
		int n = service.memberAdd(dto);
		
		//요청위임 (로그인화면 보기)
		response.sendRedirect("LoginUIServlet");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}