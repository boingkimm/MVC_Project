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
			// DAO 연동
			BoardDAO dao = new BoardDAO();
			n = dao.write(session, dto);
			session.commit();
		}finally {
			session.close();
		}
		return n;
	}

	@Override
	public BoardDTO retrieve(int num) {
		BoardDTO dto = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			// DAO 연동
			BoardDAO dao = new BoardDAO();
			//조회수 증가 후
			int n = dao.readcnt(session, num);
			session.commit();
			//글 자세히 보기
			dto = dao.retrieve(session, num);
		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public int update(BoardDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			BoardDAO dao = new BoardDAO();
			n = dao.update(session, dto);
			session.commit();
			
		} finally {
			session.close();
		}
		return n;
	}

	@Override
	public int delete(int num) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			// DAO 연동
			BoardDAO dao = new BoardDAO();
			n = dao.delete(session, num);
			session.commit();
		}finally {
			session.close();
		}
		return n;
	}

}

