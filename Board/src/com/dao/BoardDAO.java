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
	
	//글 자세히 보기
	public BoardDTO retrieve(SqlSession session, int num) {
		return session.selectOne("BoardMapper.retrieve", num);
	}
	
	//조회수 증가
	public int readcnt(SqlSession session, int num) {
		return session.update("BoardMapper.readcnt", num);
	}
}
