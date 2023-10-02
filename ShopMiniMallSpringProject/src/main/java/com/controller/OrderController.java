package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService service;
	
	@GetMapping("/OrderConfirmServlet")
	public String orderConfirm(@RequestParam("num") int num, HttpSession session, Model m) {
		//로그인 여부 확인  ==> interceptor

		//num값에 해당하는 CartDTO 얻기
		CartDTO cDTO = service.cartByNum(num);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		MemberDTO mDTO = service.memberByUserid(userid);
		
		//모델 저장
		m.addAttribute("cDTO", cDTO);
		m.addAttribute("mDTO", mDTO);
		
		return "orderConfirm";
	}
	
	
	@GetMapping("/OrderDoneServlet")
	public String orderDone(OrderDTO orderDTO, HttpSession session, @RequestParam("num") int del_num, Model m) {
		//로그인 여부 확인  ==> interceptor
		
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();  //session필요
		orderDTO.setUserid(userid);  //userid 외 g정보들은 orderDTO에 저장되어 있음
		
		//Cart 테이블에서 삭제할 num
		int n = service.orderDone(orderDTO, del_num);
		
		m.addAttribute("orderDTO", orderDTO); //모델 저장
		return "orderDone";  //뷰 지정
	}
	
}
