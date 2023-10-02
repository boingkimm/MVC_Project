package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	//TODO 맵핑값들 맘에 안듦. 나중에 수정하기
	
	@GetMapping("/MemberUIServlet")  
	public String memberUI() {
		return "memberForm";
	}
	
	@PostMapping("/MemberAddServlet")
	public String memberAdd(MemberDTO dto) {
		int n = service.memberAdd(dto);
		return "redirect:LoginUIServlet";
	}
	
	//TODO 인터셉터용 맵핑값 재설정
	
	@GetMapping("/MyPageServlet")
	public String mypage(HttpSession session) {  //로그인여부 확인위한 session
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		
		//로그인 여부 확인은 Interceptor 이용 ==> LoginCheckHandlerInterceptor
		//Interceptor에서 로그인여부 확인해서 로그인 상태여야 mypage로 넘어오는 것!
		
		String userid = dto.getUserid();
		MemberDTO mypage = service.mypage(userid);
		session.setAttribute("login", mypage);
		return "mypage"; //jsp
	}
	
	
	/*
	 * ajax 연동
	 * - @ResponseBody에 의해서 jsp가 아닌 일반데이터(문자열, JSON 형태)로 응답함
	 * - 의존성 주입 필요 : jackson-databind
	 */
	
	@GetMapping(value = "/MemberIdCheckServlet", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String idCheck(@RequestParam("userid") String userid) {
		
		MemberDTO dto = service.idCheck(userid);
		String mesg = "아이디 사용가능";  //dto가 null이면 중복된 값이 없다는 뜻
		if(dto != null) {
			mesg = "아이디 중복";
		}
		return mesg;  //string 타입 리턴
	}
	
	
	
	
}
