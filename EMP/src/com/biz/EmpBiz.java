package com.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.EmpDTO;

public interface EmpBiz {

	public List<EmpDTO> list();
	public int write(EmpDTO dto);
}
