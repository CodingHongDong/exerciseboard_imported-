package com.hong.fitness.service;

import java.util.List;

import com.hong.fitness.vo.FitnessBoardReplyVO;

public interface FitnessBoardReplyService {

	// 댓글 조회
	public List<FitnessBoardReplyVO> replyList(int no) throws Exception;
	
	// 댓글 작성
	public void replyWrite(FitnessBoardReplyVO fbvo) throws Exception;
	
	// 댓글 수정
	public int replyModify(FitnessBoardReplyVO fbvo) throws Exception;
	
	// 댓글 삭제
	public void replyDelete(int rno) throws Exception;
	
}
