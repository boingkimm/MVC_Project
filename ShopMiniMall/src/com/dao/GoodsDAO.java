package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.GoodsDTO;

public class GoodsDAO {

	//상품목록
	public List<GoodsDTO> goodsList(SqlSession session, String gCategory) {
		List<GoodsDTO> list = session.selectList("GoodsMapper.goodsList", gCategory);
		return list;
	}
}
