package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.BoardDTO;
import com.service.BoardService;
import com.service.BoardServiceImpl;

@WebServlet("/write")
public class BoardWriteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setAuthor(author);
		dto.setContent(content);

		//서비스 거쳐서 DAO까지 전달
		BoardService service = new BoardServiceImpl();
		int n = service.write(dto);
		
		//목록보기(BoardListServlet)에 요청위임  (list.jsp아님)
		response.sendRedirect("list");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
