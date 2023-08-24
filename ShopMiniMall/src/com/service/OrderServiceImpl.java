package com.service;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.OrderDAO;
import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;

import oracle.net.aso.s;

public class OrderServiceImpl implements OrderService {

	@Override
	public CartDTO cartByNum(int num) {
		CartDTO cartDTO = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			OrderDAO dao = new OrderDAO();
			cartDTO = dao.cartByNum(session, num);
		} finally {
			session.close();
		}
		return cartDTO;

	}

	@Override
	public MemberDTO memberByUserid(String userid) {
		MemberDTO memberDTO = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			OrderDAO dao = new OrderDAO();
			memberDTO = dao.memberByUserid(session, userid);
		} finally {
			session.close();
		}
		return memberDTO;
	}

	@Override
	public int orderDone(OrderDTO dto, int del_num) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동코드
			OrderDAO dao = new OrderDAO();
		/////트랜잭션 시작
			//orderinfo테이블에 저장
			n = dao.orderDone(session, dto);
			//cart테이블에서 삭제
			n = dao.cartDel(session, del_num);
			
			session.commit();
		/////트랜잭션 종료
		}catch (Exception e) {
			session.rollback();
		}finally {
			session.close();
		}
		return n;
	}

}

