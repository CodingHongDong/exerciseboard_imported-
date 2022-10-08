package com.hong.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hong.fitness.service.FitnessBoardReplyServiceImpl;
import com.hong.fitness.vo.FitnessBoardReplyVO;
import com.hong.util.domain.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/reply/*")
@Log4j
public class FitnessBoardReplyController {

	@Autowired
	private FitnessBoardReplyServiceImpl fbrservice;
	
	// 댓글 조회
	
	// 댓글 작성
	@PostMapping("/write.do")
	public String postWrite(FitnessBoardReplyVO fbvo, PageObject pageObject) throws Exception {
		
		fbrservice.replyWrite(fbvo);
		
		log.info("댓글 작성 : " + fbvo);
		
		return "redirect:http://localhost/hong/fitnessboard/view.do?no=" + fbvo.getNo()
				+ "&inc=0"
				+ "&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	
	// 댓글 수정 폼
	@GetMapping("/update.do")
	public String replyUpdate(int rno, Model model, PageObject pageObject) throws Exception {
		
		List<FitnessBoardReplyVO> fbvo = fbrservice.replyList(rno);
		
		model.addAttribute("fbvo", fbvo);
		
		log.info("댓글 수정 폼");
		
		return "hong/fitnessboard/reply/update";
	}
	
	// 댓글 수정 처리
	
	
	
	// 댓글 삭제 
	//@GetMapping("/delete.do")
	@RequestMapping(value="/delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String replyDelete(int rno, long no, Model model, @ModelAttribute PageObject pageObject) throws Exception {
		
		log.info("===  reply delete requested 댓글 번호: " + rno + ", 게시물 번호: "+ no);
		
		fbrservice.replyDelete(rno);
		log.info("---  replyDelete Mapper executed");
		
		model.addAttribute("pageObject", pageObject);
		log.info("---  PageObject Set: " + pageObject);
		log.info("댓글 삭제 해당 댓글의 번호 :" + rno + ", 해당 댓글이 속한 게시물의 번호 : " + no);
		
		return "redirect:http://localhost/hong/fitnessboard/view.do?no=" + no
				+ "&inc=0"
				+ "&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum();
	}
}
