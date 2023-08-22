package com.service;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;

public interface MemberService {

	public MemberDTO idCheck(String userid);
	public int memberAdd(MemberDTO dto);
}
