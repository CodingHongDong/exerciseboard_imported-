package com.hong.fitness.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hong.fitness.mapper.FitnessBoardMapper;
import com.hong.fitness.vo.FitnessBoardVO;
import com.hong.util.domain.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class FitnessBoardServiceImpl implements FitnessBoardService {

	@Inject
	private FitnessBoardMapper mapper;
	
	// 게시판 리스트
	@Override
	public List<FitnessBoardVO> list(PageObject pageObject) throws Exception {
		
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		
		log.info("pageObject : " + pageObject);
		
		return mapper.list(pageObject);
	}
	
	// 게시판 글보기
	@Override
	public FitnessBoardVO view(long no) throws Exception {
		
		
		return mapper.view(no);
	}

	// 게시판 글 등록
	@Override
	public int write(FitnessBoardVO vo) throws Exception {
		
		return mapper.write(vo);
	}

	// 게시판 글 수정
	@Override
	public int update(FitnessBoardVO vo) throws Exception {
		
		log.info("업데이트 vo : " + vo);
		
		return mapper.update(vo);
	}

	// 게시판 글 삭제
	@Override
	public int delete(long no) throws Exception {
		
		return mapper.delete(no);
	}

	// 조회수 1 증가
	@Override
	public int increase(long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.increase(no);
	}

	
}
