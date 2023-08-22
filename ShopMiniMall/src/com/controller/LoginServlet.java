package com.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//아이디, 비번 가져오기
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		//hashmap에 담기 (dto도 가능)
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		
		//hashmap을 서비스 거쳐서 dao까지 전달
		MemberService service = new MemberServiceImpl();
		MemberDTO dto = service.login(map);
		
		String nextPage = null;
		if (dto != null) {
			// 로그인 성공시 세션 처리 후 메인으로 감
			nextPage = "main";
			
			//세션처리
			HttpSession session = request.getSession();
			session.setAttribute("login", dto);
			// => session scope에 저장된 login 키의 존재 여부에 따라 로그인 여부 알 수 있음
			// 세션에 키값 있으면 로그인된 화면 (안녕하세요 회원님) : top.jsp
		} else {
			//dto가 null이면 userid 또눈 passwd 틀린 경우
			nextPage = "member/loginFail.jsp";
		}
		
		//요청위임 - 로그인 성공, 실패 시 다른 화면으로 가도록
		response.sendRedirect(nextPage);
	}

}
