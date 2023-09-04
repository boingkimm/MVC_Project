package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.BoardDTO;
import com.dto.PageDTO;

public class BoardDAO {

	//전체목록
	public PageDTO list(SqlSession session, HashMap<String, String> map, int curPage) {
		
		PageDTO pageDTO = new PageDTO();
		
		int offset = (curPage-1)*pageDTO.getPerPage(); //시작위치 (0, 3, 6,..) : (현재페이지-1)*페이지당 보여줄 개수
		int limit = pageDTO.getPerPage();  //한 페이지당 보여줄 개수 (3)
		
		List<BoardDTO> list = session.selectList("BoardMapper.list", map, 
				new RowBounds(offset, limit));
		
		//list, curPage 저장
		pageDTO.setList(list);
		pageDTO.setCurPage(curPage);
		
		//totalCount 저장
		int totalCount = 0;
		if(map.get("searchValue")==null) {
			//검색 안한 경우
			totalCount = session.selectOne("totalCount");
		}else {
			//검색 한 경우
			totalCount = session.selectOne("totalCountSearch", map);
		}
		pageDTO.setTotalCount(totalCount);
		
		//searchName, searchValue 모두 PageDTO에 저장 필요 (검색시에도 페이징 유지위해)
		pageDTO.setSearchName(map.get("searchName"));
		pageDTO.setSearchValue(map.get("searchValue"));
		
		return pageDTO;
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
	
	//글 수정
	public int update (SqlSession session, BoardDTO dto) {
		int n = session.update("BoardMapper.update", dto);
		return n;
	}
	
	//글 삭제
	public int delete (SqlSession session, int num) {
		int n = session.delete("BoardMapper.delete", num);
		return n;
	}
	
}
