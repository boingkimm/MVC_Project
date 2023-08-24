package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;

public class OrderDAO {

	public CartDTO cartByNum(SqlSession session, int num) {
		return session.selectOne("OrderMapper.cartByNum", num);
	}

	public MemberDTO memberByUserid(SqlSession session, String userid) {
		return session.selectOne("OrderMapper.memberByUserid", userid);
	}
	
	public int orderDone(SqlSession session, OrderDTO dto) {
		return session.insert("OrderMapper.orderDone", dto);
	}
	
	public int cartDel(SqlSession session, int del_num) {
		return session.delete("OrderMapper.cartDel", del_num);
	}
}
