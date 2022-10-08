package com.hong.fitness.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hong.fitness.service.FitnessBoardReplyServiceImpl;
import com.hong.fitness.service.FitnessBoardService;
import com.hong.fitness.vo.FitnessBoardReplyVO;
import com.hong.fitness.vo.FitnessBoardVO;
import com.hong.util.domain.PageObject;


import lombok.extern.log4j.Log4j;

// fitness 게시판
@Controller
@Log4j
@RequestMapping("/hong/fitnessboard")
public class FitnessBoardController {
	
	@Autowired
	private FitnessBoardService fitnessBoardServiceImpl;
	
	@Autowired
	private FitnessBoardReplyServiceImpl replyService;
	
	// 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model, @ModelAttribute PageObject pageObject) throws Exception {
		
		List<FitnessBoardVO> list = fitnessBoardServiceImpl.list(pageObject);
		
		log.info(list.size());
		
		model.addAttribute("vo", list);
		model.addAttribute("pageObject", pageObject);
		
		return "hong/fitnessboard/list";
	}
	
	// 게시판 글보기
	@GetMapping("/view.do")
	public String view(@RequestParam("no") int no, Model model, HttpSession session, FitnessBoardVO vo, PageObject pageObject) throws Exception {
		
		log.info("fitness board 글보기 no : " + no);
		
		model.addAttribute("vo", fitnessBoardServiceImpl.view(no));
		model.addAttribute("pageObject", pageObject);
		
		fitnessBoardServiceImpl.increase(no);
		
		// 댓글 조회
		List<FitnessBoardReplyVO> reply = replyService.replyList(no);
		model.addAttribute("reply", reply);
		
		
		return "hong/fitnessboard/view";
	}
	
	
	// 게시판 글쓰기 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception {
		
		log.info("게시판 글쓰기 폼");
		
		return "hong/fitnessboard/write";
	}
	
	// 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String write(FitnessBoardVO vo, int perPageNum) throws Exception {
		
		log.info("게시판 글쓰기 처리 vo : " + vo);
		
		fitnessBoardServiceImpl.write(vo);
		
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	}
	
	// 게시판 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) throws Exception{
		model.addAttribute("vo", fitnessBoardServiceImpl.view(no));
		
		log.info("게시판 수정 폼 no : " + no);		
		
		return "hong/fitnessboard/update";
	}
	
	// 게시판 글수정 처리
	@PostMapping("/update.do")
	public String update(FitnessBoardVO vo, PageObject pageObject) throws Exception {
		
		log.info("게시판 글 수정 처리 vo : " + vo);
		
		fitnessBoardServiceImpl.update(vo);
		
		return "redirect:view.do?no=" + vo.getNo()
				+ "&inc=0"
				+ "&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 게시판 글삭제
	@GetMapping("/delete.do")
	public String delete(FitnessBoardVO vo, int perPageNum) throws Exception {
		log.info("게시판 삭제 : " + vo);
		
		fitnessBoardServiceImpl.delete(vo.getNo());
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
	
	// 댓글 삭제 get
	//public String replyDelete
	
}
