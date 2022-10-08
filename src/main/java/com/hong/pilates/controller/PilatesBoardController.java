package com.hong.pilates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hong.pilates.service.PilatesBoardService;
import com.hong.pilates.vo.PilatesBoardVO;
import com.hong.util.domain.PageObject;

import lombok.extern.log4j.Log4j;

// pilates 게시판
@Controller
@RequestMapping("/hong/pilatesboard")
@Log4j
public class PilatesBoardController {

	@Autowired
	private PilatesBoardService pilatesBoardServiceImpl;
	
	// 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model, PageObject pageObject) throws Exception {
		
		List<PilatesBoardVO> list = pilatesBoardServiceImpl.list(pageObject);
		
		log.info("게시판 리스트");
		
		model.addAttribute("vo", list);
		model.addAttribute("pageObject", pageObject);
		
		return "hong/pilatesboard/list";
	}
	
	// 게시판 글보기
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception {
		
		log.info("pilates board 글보기 no : " + no);
		
		model.addAttribute("vo", pilatesBoardServiceImpl.view(no));
		
		pilatesBoardServiceImpl.increase(no);
		
		return "hong/pilatesboard/view";
	}
	
	
	// 게시판 글쓰기 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception {
		
		log.info("게시판 글쓰기 폼");
		
		return "hong/pilatesboard/write";
	}
	
	// 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String write(PilatesBoardVO vo, int perPageNum) throws Exception {
		
		log.info("게시판 글쓰기 처리 vo : " + vo);
		
		pilatesBoardServiceImpl.write(vo);
		
		return "redirect:list.do?page=1&perPageNum" + perPageNum;
	}
	
	// 게시판 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) throws Exception{
		model.addAttribute("vo", pilatesBoardServiceImpl.view(no));
		
		log.info("게시판 수정 폼 no : " + no);		
		
		return "hong/pilatesboard/update";
	}
	
	// 게시판 글수정 처리
	@PostMapping("/update.do")
	public String update(PilatesBoardVO vo, PageObject pageObject) throws Exception {
		
		log.info("글 수정 처리 vo : " + vo);
		
		pilatesBoardServiceImpl.update(vo);
		
		return "redirect:view.do?no=" + vo.getNo()
				+ "&inc=0"
				+ "&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 게시판 글삭제
	@GetMapping("/delete.do")
	public String delete(PilatesBoardVO vo, int perPageNum) throws Exception {
		
		log.info("게시판 삭제 : " + vo);
		
		pilatesBoardServiceImpl.delete(vo.getNo());
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
}
