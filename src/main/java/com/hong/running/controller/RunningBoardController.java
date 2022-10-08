package com.hong.running.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hong.running.service.RunningBoardService;
import com.hong.running.vo.RunningBoardVO;
import com.hong.util.domain.PageObject;

import lombok.extern.log4j.Log4j;

// running 게시판
@Controller
@RequestMapping("/hong/runningboard")
@Log4j
public class RunningBoardController {

	@Autowired
	private RunningBoardService runningBoardServiceImpl;
	
	// 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model, PageObject pageObject) throws Exception {
		
		List<RunningBoardVO> list = runningBoardServiceImpl.list(pageObject);
		
		log.info("게시판 리스트" + list.size());
		
		model.addAttribute("vo", list);
		model.addAttribute("pageObject", pageObject);
		
		return "hong/runningboard/list";
	}
	
	// 게시판 글보기
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception {
		
		log.info("running board 글보기 no : " + no);
		
		model.addAttribute("vo", runningBoardServiceImpl.view(no));
		
		runningBoardServiceImpl.increase(no);
		
		return "hong/runningboard/view";
	}
	
	
	// 게시판 글쓰기 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception {
		
		log.info("게시판 글쓰기 폼");
		
		return "hong/runningboard/write";
	}
	
	// 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String write(RunningBoardVO vo, int perPageNum) throws Exception {
		
		log.info("게시판 글쓰기 처리 vo : " + vo);
		
		runningBoardServiceImpl.write(vo);
		
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	}
	
	// 게시판 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) throws Exception{
		model.addAttribute("vo", runningBoardServiceImpl.view(no));
		
		log.info("게시판 수정 폼 no : " + no);		
		
		return "hong/runningboard/update";
	}
	
	// 게시판 글수정 처리
	@PostMapping("/update.do")
	public String update(RunningBoardVO vo, PageObject pageObject) throws Exception {
		
		log.info("게시판 글 수정 처리 vo : " + vo);
		
		runningBoardServiceImpl.update(vo);
		
		return "redirect:view.do?no=" + vo.getNo()
				+ "&inc=0"
				+ "&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 게시판 글삭제
	@GetMapping("/delete.do")
	public String delete(RunningBoardVO vo, int perPageNum) throws Exception {
		
		log.info("게시판 삭제 : " + vo);
		
		runningBoardServiceImpl.delete(vo.getNo());
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
}