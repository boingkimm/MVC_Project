package com.service;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;

public interface CartService {

	public int cartAdd(CartDTO dto);
}
