package com.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	GoodsService service;
	
	@Autowired
	CartService cartService;
	
	//모델을 저장하는 방법1 - Model 파라미터
//	@GetMapping("/GoodsRetrieveServlet")
//	public String retrieve(@RequestParam("gCode") String gCode, Model m) {
//		GoodsDTO dto = service.goodsRetrieve(gCode);  //모델
//		return "goodsRetrieve";  //뷰
//	}
	
	//모델을 저장하는 방법2 - Model 리턴타입, 맵핑값
	@GetMapping("/goodsRetrieve")  // /WEB-INF/views/goodsRetrieve.jsp
	@ModelAttribute("goodsRetrieve")   // "/"없음 주의!!
	public GoodsDTO retrieve(@RequestParam("gCode") String gCode) {
		GoodsDTO dto = service.goodsRetrieve(gCode);  //모델
		return dto;  //모델을 리턴 => 키값:goodsDTO에 저장
	}
	
	
	@GetMapping("/CartAddServlet")
	public String cartAdd(HttpSession session, CartDTO cartDTO) {
		//로그인 여부 확인  ==> interceptor
		//여기 왔다는 것은 로그인이 되었다는 뜻
		
		MemberDTO dto = (MemberDTO)session.getAttribute("login");  //세션에서 dto 얻고 (세션은 파라미터에)
		String userid = dto.getUserid();  //dto에서 아이디 얻기
		cartDTO.setUserid(userid);
		
		int n = cartService.cartAdd(cartDTO);
		
		return "goods/cartAddSuccess";
	}
	
	
	//모델을 저장하는 방법2 - ModelAndView
	@GetMapping("/CartListServlet")
	public ModelAndView cartList(HttpSession session) {
		//로그인 여부 확인  ==> interceptor
		
		MemberDTO dto = (MemberDTO)session.getAttribute("login");  //userid 필요하므로 세션 필요
		String userid = dto.getUserid();
		
		List<CartDTO> list = cartService.cartList(userid);  //list가 모델이 됨
		
		//모델(list)을 저장 
		ModelAndView mav = new ModelAndView();
		mav.addObject("cartList", list);  //모델 저장
		mav.setViewName("cartList");      //뷰 저장
		return mav;
	}

	
	@GetMapping("/CartUpdateServlet")
	@ResponseBody
	public void cartUpdate(@RequestParam HashMap<String, Integer> map) {
		//로그인 여부 확인  ==> interceptor
		//주문번호만 있으면 됨 - HashMap에 있으므로 userid 필요 없음. 세션 필요 없음
		
		int n = cartService.cartUpdate(map);  //끝. 리턴 없음 void
	}

	
	@GetMapping("/CartDeleteServlet")
	public String cartDel(@RequestParam("num") int num) {
		//로그인 여부 확인  ==> interceptor
		
		int n = cartService.cartDelete(num);
		return "redirect:CartListServlet";
	}
	
	
	@GetMapping("/CartDeleteAllServlet")
	public String cartDelAll(HttpServletRequest request) {   //파라미터로 request 바로 받기
		//선택항목 전체 삭제하기
		//로그인 여부 확인  ==> interceptor
		String [] check = request.getParameterValues("check"); //배열로 저장됨
		List<String> del_list = Arrays.asList(check);  //배열을 리스트로 변경
		
		int n = cartService.cartDeleteAll(del_list);
		
		return "redirect:CartListServlet";
	}

	
	
}

