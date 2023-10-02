package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@Controller
public class MainController {

	//MainServlet과 비교
	
	//http://localhost:8099/shop/main
	
	@Autowired
	GoodsService service; //GoodsService service = new GoodsServiceImpl();

	@GetMapping("/main")
	public String main(@RequestParam(value="gCategory", required = false, defaultValue = "top") String gCategory,
						Model m) {
		List<GoodsDTO> list = service.goodsList(gCategory); //List<GoodsDTO> list가 Model이다
		m.addAttribute("goodsList", list);  //scope저장. request.setAttribute("goodsList", list);
		
		return "main";
	}
}
