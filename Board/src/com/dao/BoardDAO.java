package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.BoardDTO;

public class BoardDAO {

	//전체목록
	public List<BoardDTO> list(SqlSession session) {
		return session.selectList("BoardMapper.list");
	}

	//글 저장
	public int write(SqlSession session, BoardDTO dto) {
		return session.insert("BoardMapper.write", dto);
	}
	
}
