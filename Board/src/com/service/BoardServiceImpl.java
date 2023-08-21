package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.BoardDAO;
import com.dto.BoardDTO;

public class BoardServiceImpl implements BoardService {

	@Override
	public List<BoardDTO> list() {
		List<BoardDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			BoardDAO dao = new BoardDAO();
			list = dao.list(session);
			
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public int write(BoardDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			BoardDAO dao = new BoardDAO();
			n = dao.write(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

}

