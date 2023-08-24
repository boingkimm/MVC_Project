package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dto.CartDTO;

import sun.print.resources.serviceui;

public class CartServiceImpl implements CartService {

	@Override
	public int cartAdd(CartDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			CartDAO dao = new CartDAO();
			n = dao.cartAdd(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	@Override
	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			CartDAO dao = new CartDAO();
			list = dao.cartList(session, userid);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int cartUpdate(HashMap<String, Integer> map) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			CartDAO dao = new CartDAO();
			n = dao.cartUpdate(session, map);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	@Override
	public int cartDelete(int num) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			CartDAO dao = new CartDAO();
			n = dao.cartDelete(session, num);
			session.commit();
		} finally {
			session.close();
		}
		return n;

	}

}
