package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;

public class CartDAO {

	//장바구니 저장
	public int cartAdd(SqlSession session, CartDTO dto) {
		int n = session.insert("CartMapper.cartAdd", dto);
		return n;
	}

	//장바구니 목록보기
	public List<CartDTO> cartList(SqlSession session, String userid) {
		return session.selectList("CartMapper.cartList", userid);
	}
}
