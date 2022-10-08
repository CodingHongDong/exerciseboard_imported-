package com.hong.fitness.service;

import java.util.List;

import com.hong.fitness.vo.FitnessBoardVO;
import com.hong.util.domain.PageObject;


public interface FitnessBoardService {

	// 게시판 리스트
	public List<FitnessBoardVO> list(PageObject pageObject) throws Exception;
	
	// 게시판 글보기
	public FitnessBoardVO view(long no) throws Exception;
	
	// 게시판 글쓰기
	public int write(FitnessBoardVO vo) throws Exception;
	
	// 게시판 글 수정
	public int update(FitnessBoardVO vo) throws Exception;
	
	// 게시판 글 삭제
	public int delete(long no) throws Exception;
	
	// 조회수 1 증가
	public int increase(long no) throws Exception;
}
