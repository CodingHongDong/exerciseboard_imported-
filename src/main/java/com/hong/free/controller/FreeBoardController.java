package com.hong.free.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hong.free.service.FreeBoardService;
import com.hong.free.vo.FreeBoardVO;
import com.hong.util.domain.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/hong/freeboard")
@Log4j
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardServiceImpl;
	
	// 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model, @ModelAttribute PageObject pageObject) throws Exception {
		
		List<FreeBoardVO> list = freeBoardServiceImpl.list(pageObject);
		
		log.info("게시판 리스트");
		
		model.addAttribute("vo", list);
		model.addAttribute("pageObject", pageObject);
		
		return "hong/freeboard/list";
	}
	
	// 게시판 글보기
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception {
		
		log.info("free board 글보기 no : " + no);
		
		model.addAttribute("vo", freeBoardServiceImpl.view(no));
		
		freeBoardServiceImpl.increase(no);
		
		return "hong/freeboard/view";
	}
	
	
	// 게시판 글쓰기 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception {
		
		log.info("게시판 글쓰기 폼");
		
		return "hong/freeboard/write";
	}
	
	// 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String write(FreeBoardVO vo, int perPageNum) throws Exception {
		
		log.info("게시판 글쓰기 처리 vo : " + vo);
		
		freeBoardServiceImpl.write(vo);
		
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	}
	
	// 게시판 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) throws Exception{
		model.addAttribute("vo", freeBoardServiceImpl.view(no));
		
		log.info("게시판 수정 폼 no : " + no);		
		
		return "hong/freeboard/update";
	}
	
	// 게시판 글수정 처리
	@PostMapping("/update.do")
	public String update(FreeBoardVO vo, PageObject pageObject) throws Exception {
		
		log.info("게시판 글 수정 처리 vo : " + vo);
		
		freeBoardServiceImpl.update(vo);
		
		return "redirect:view.do?no=" + vo.getNo()
				+ "&inc=0"
				+ "&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 게시판 글삭제
	@GetMapping("/delete.do")
	public String delete(FreeBoardVO vo, int perPageNum) throws Exception {
		
		log.info("게시판 삭제 : " + vo);
		
		freeBoardServiceImpl.delete(vo.getNo());
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
}