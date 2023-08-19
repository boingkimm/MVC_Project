package com.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.EmpDAO;
import com.dto.EmpDTO;

public class EmpBizImpl implements Empbiz {

	@Override
	public List<EmpDTO> list() {
		List<EmpDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			EmpDAO dao = new EmpDAO();
			list = dao.list(session);
		} finally {
			session.close();
		}
		return list;
	}

}
