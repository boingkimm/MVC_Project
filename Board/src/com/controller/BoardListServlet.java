package com.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.PageDTO;
import com.service.BoardService;
import com.service.BoardServiceImpl;

@WebServlet("/list")
public class BoardListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// list.jsp에서 1 2 3 .. 페이지 번호 클릭할 때 전달된 현재 페이지 번호 얻기
		// <a href="list?curPage=2>2</a>  => 2를 DAO(offset)까지 넘겨야 함
		String curPage = request.getParameter("curPage");
		if(curPage==null) {  //맨 처음 실행한 경우 (첫 페이지)
			curPage = "1";
		}
		
		//검색 파라미터 얻기
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		
		//2개의 값을 서비스 거쳐서 DAO에 전달
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		
		
		//BoardService 연동
		BoardService service = new BoardServiceImpl();
		PageDTO pageDTO = service.list(map, Integer.parseInt(curPage));   //map, curPage 추가
		
		//List<BoardDTO>를 scope에 저장
		request.setAttribute("pageDTO", pageDTO);
		
		//요청위임
		request.getRequestDispatcher("list.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
