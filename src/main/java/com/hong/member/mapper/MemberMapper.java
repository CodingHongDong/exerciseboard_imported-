package com.hong.member.mapper;

import com.hong.member.vo.LoginVO;
import com.hong.member.vo.MemberVO;

public interface MemberMapper {

	// 로그인 처리
	public LoginVO login(LoginVO invo) throws Exception;
	
	// 회원가입 처리
	public int write(MemberVO vo) throws Exception;
	
	// 아이디 중복 체크
	public String idCheck(String id) throws Exception;
	
}
