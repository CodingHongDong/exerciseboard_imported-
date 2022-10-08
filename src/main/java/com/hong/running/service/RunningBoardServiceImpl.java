package com.hong.running.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hong.running.mapper.RunningBoardMapper;
import com.hong.running.vo.RunningBoardVO;
import com.hong.util.domain.PageObject;

@Service
public class RunningBoardServiceImpl implements RunningBoardService {

	@Inject
	private RunningBoardMapper mapper;
	
	@Override
	public List<RunningBoardVO> list(PageObject pageObject) throws Exception {
		
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		
		return mapper.list(pageObject);
	}

	@Override
	public RunningBoardVO view(long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.view(no);
	}

	@Override
	public int write(RunningBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

	@Override
	public int update(RunningBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.delete(no);
	}

	@Override
	public int increase(long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.increase(no);
	}

}
