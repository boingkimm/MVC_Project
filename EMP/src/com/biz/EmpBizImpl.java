package com.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.EmpDAO;
import com.dto.EmpDTO;

public class EmpBizImpl implements EmpBiz {

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

	@Override
	public int write(EmpDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			EmpDAO dao = new EmpDAO();
			n = dao.write(session, dto);
			session.commit();
		}finally {
			session.close();
		}
		return n;
	}

}
