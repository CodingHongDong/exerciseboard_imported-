package com.hong.running.mapper;

import java.util.List;

import com.hong.running.vo.RunningBoardVO;
import com.hong.util.domain.PageObject;

public interface RunningBoardMapper {

	// 게시판 리스트
	public List<RunningBoardVO> list(PageObject pageObject) throws Exception;
	
	// 전체 데이터 개수
	public int getTotalRow(PageObject pageObject) throws Exception;
	
	// 게시판 글보기
	public RunningBoardVO view(long no) throws Exception;
	
	// 게시판 글쓰기
	public int write(RunningBoardVO vo) throws Exception;
	
	// 게시판 글 수정
	public int update(RunningBoardVO vo) throws Exception;
	
	// 게시판 글 삭제
	public int delete(long no) throws Exception;
	
	// 조회수 1 증가
	public int increase(long no) throws Exception;
	
}
