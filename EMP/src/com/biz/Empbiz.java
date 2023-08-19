package com.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.EmpDTO;

public interface Empbiz {

	public List<EmpDTO> list();
}
