package com.service;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MemberDAO;
import com.dto.MemberDTO;

public class MemberServiceImpl implements MemberService {

	@Override
	public MemberDTO idCheck(String userid) {
		MemberDTO dto = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			MemberDAO dao = new MemberDAO();
			dto = dao.idCheck(session, userid);
		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public int memberAdd(MemberDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			MemberDAO dao = new MemberDAO();
			n = dao.memberAdd(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return n;

	}



}
