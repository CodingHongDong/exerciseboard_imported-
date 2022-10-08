package com.hong.member.service;

import com.hong.member.vo.LoginVO;
import com.hong.member.vo.MemberVO;

public interface MemberService {

	// 로그인
	public LoginVO login(LoginVO invo) throws Exception;
	
	// 회원가입
	public int write(MemberVO vo) throws Exception;

	// 아이디 중복 체크
	public String idCheck(String id) throws Exception;
	
	
}
