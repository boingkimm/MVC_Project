package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {

//	@GetMapping("/LoginUIServlet")
//	public String loginUI() {
//		return "loginForm";
//	}
//  ==> view만 보여주는 코드
//      servlet-context.xml에서 구현 가능 : view-controller 작성 ***
	
	
	@Autowired
	MemberService service;
	
	@GetMapping("/LoginServlet")
	public String login(@RequestParam HashMap<String, String> map, HttpSession session) {
		
		MemberDTO dto = service.login(map);
		String nextPage = null;
		if (dto != null) {
			//로그인 성공시 세션 처리 후 메인으로 감
			//파라미터에 세션 선언 후 세션에 저장
			session.setAttribute("login", dto);
			nextPage = "redirect:main";   //WEB-INF/views/main.jsp
		}else {
			nextPage = "member/loginFail";   //WEB-INF/views/member/loginFail.jsp
		}
		return nextPage;
	}
	
	@GetMapping("/LogoutServlet")
	public String logout(HttpSession session) {
		//로그인 여부 확인   ==> LoginCheckHandlerInterceptor
		session.invalidate(); //로그아웃
		return "redirect:main";
	}
	
}
