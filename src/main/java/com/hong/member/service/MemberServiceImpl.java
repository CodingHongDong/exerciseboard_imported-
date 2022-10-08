package com.hong.member.service;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.hong.member.mapper.MemberMapper;
import com.hong.member.vo.LoginVO;
import com.hong.member.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberMapper mapper;
	
	
	// 로그인 처리
	@Override
	public LoginVO login(LoginVO invo) throws Exception {
		
		log.info("invo : " + invo);
		
		return mapper.login(invo);
	}

	// 회원가입 처리
	@Override
	public int write(MemberVO vo) throws Exception {
		
		log.info("vo : " + vo);
		
		return mapper.write(vo);
	}

	// id 중복 처리
	@Override
	public String idCheck(String id) throws Exception {
		
		return mapper.idCheck(id);
	}

}
