package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.BoardDTO;

public class BoardDAO {

	//전체목록
	public List<BoardDTO> list(SqlSession session) {
		List<BoardDTO> list = session.selectList("BoardMapper.list");
		return list;
	}

	//글저장
	public int write(SqlSession session, BoardDTO dto) {
		int n = session.insert("BoardMapper.write", dto);
		return n;
	}


}
