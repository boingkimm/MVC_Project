package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

@WebServlet("/MemberIdCheckServlet")
public class MemberIdCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");  //입력된 아이디값이 넘어옴

		//userid로 서비스 연동
		MemberService service = new MemberServiceImpl();
		MemberDTO dto = service.idCheck(userid);
		
		String mesg = "아이디 사용가능";  //dto가 null이면 중복된 값이 없다는 뜻
		if(dto != null) {
			mesg = "아이디 중복";
		}
		// => ajax를 사용하여 응답 처리할 것임
		
		//ajax이용해서 응답처리 - 요청위임 필요X
		response.setContentType("text/plain;charset=utf-8");  //text(mesg)를 응답
		PrintWriter out = response.getWriter();
		out.print(mesg);
		// => 얘를 요청한 곳에서 mesg 메시지를 받게 됨
		
	}

}
