package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;

public class OrderDAO {

	public CartDTO cartByNum(SqlSession session, int num) {
		return session.selectOne("OrderMapper.cartByNum", num);
	}

	public MemberDTO memberByUserid(SqlSession session, String userid) {
		return session.selectOne("OrderMapper.memberByUserid", userid);
	}
}
