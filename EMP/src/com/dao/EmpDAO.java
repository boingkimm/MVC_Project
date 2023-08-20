package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.EmpDTO;

public class EmpDAO {

	//전체목록
	public List<EmpDTO> list(SqlSession session) {
		List<EmpDTO> list = session.selectList("EmpMapper.list");
		return list;
	}
	
	//사원 등록
	public int write(SqlSession session, EmpDTO dto) {
		int n  = session.selectOne("EmpMapper.write", dto);
		return n;
	}
}
