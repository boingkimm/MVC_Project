package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OrderDAO;
import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO dao;
	
	@Override
	public CartDTO cartByNum(int num) {
		CartDTO cartDTO = dao.cartByNum(num);
		return cartDTO;
	}

	@Override
	public MemberDTO memberByUserid(String userid) {
		MemberDTO memberDTO = dao.memberByUserid(userid);
		return memberDTO;
	}

	@Override
	@Transactional
	public int orderDone(OrderDTO dto, int del_num) {
		//트랜잭션 : 두 메서드 모두 성공해야 commit, 하나라도 런타임예외 발생 시 모두  rollback
		int n = dao.orderDone(dto); //orderinfo테이블에 저장
			n = dao.cartDel(del_num); //cart테이블에서 삭제
		return n;
	}

}

