package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dto.MemberDTO;

@Component("xxx")
public class LoginCheckHandlerInterceptor extends HandlerInterceptorAdapter {

	//Interceptor : 가로채기! (=filter)
	//1. HandlerInterceptorAdapter 상속받고 재정의
	//2. servlet-context.xml에 빈 등록 (component-scan)
	//3. servlet-context.xml에 경로 등록 
	
	
	//주요 기능 : request 이용해서 특별한 작업 수행 ==> 주로 로그인 여부 확인 작업
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyHandlerInterceptor.preHandle");
		
		//로그인 체크 여부 확인
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		if (dto==null) {
			response.sendRedirect("/shop/LoginUIServlet");  //절대경로  (LoginUIServlet -> loginForm.jsp)
			return false; //Controller로 요청되지 않음
		}
		return true; //Controller로 요청됨
	}

	
	
	
	
	
	
	
	
	//주요 기능 : request 및 ModelAndView 이용해서 특별한 작업 수행 ==> 주로 ModelAndView 이용해서 view정보 변경 작업 가능
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MyHandlerInterceptor.postHandle");
	}

	//잘 사용하지 않음
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyHandlerInterceptor.afterCompletion");
	}

	
}
