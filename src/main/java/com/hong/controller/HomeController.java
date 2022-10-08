package com.hong.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hong.member.service.MemberService;
import com.hong.member.vo.LoginVO;
import com.hong.member.vo.MemberVO;

import lombok.extern.log4j.Log4j;

// 메인페이지 컨트롤러
@Controller
@Log4j
@RequestMapping("/hong")
public class HomeController {
	
	@Autowired
	private MemberService memberServiceImpl;
	
	// 메인 페이지
	@GetMapping("/home.do")
	public String homeView() {
		log.info("메인 페이지");
		
		return "hong/home";
	}
	
	// 로그인 폼
	@GetMapping("/login.do")
	public String loginForm() throws Exception{
		
		log.info("login 폼으로 이동");
		return "hong/login";
	}
	
	// 로그인 처리
	@PostMapping("/login.do")
	// 사용자가 아이디와 비밀번호를 입력해서 보낸다. -> 받는다.
	public String login(LoginVO invo, HttpSession session, RedirectAttributes rttr) throws Exception{
		log.info("로그인 처리 - invo : " + invo);
		
		LoginVO loginVO = memberServiceImpl.login(invo);
		
		if (loginVO != null) {
			session.setAttribute("login", loginVO);
			
			return "redirect:/hong/home.do";
		} else {
			return "redirect:/hong/login.do";
		}
		
	}
	
	// 로그아웃 처리
	@GetMapping("/logout.do")
	public String logout(HttpSession session) throws Exception{
		// 로그아웃 처리 - session의 정보를 지운다.
		session.removeAttribute("login");
		
		log.info("로그아웃 처리됨");
		
		return "redirect:/hong/home.do";
	}
	
	// 회원가입 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception{
		log.info("회원가입 폼");
		
		return "hong/write";
	}
	
	// 회원가입 처리
	@PostMapping("/write.do")
	public String write(MemberVO vo) throws Exception{
		
		memberServiceImpl.write(vo);
		
		log.info("회원가입 성공 ! vo : " + vo);
		
		return "redirect:/hong/login.do";
	}
	
	// 아이디 중복 처리
	@GetMapping("/idCheck")
	public String idCheck(String id, Model model) throws Exception {
		
		model.addAttribute("id", memberServiceImpl.idCheck(id));
		
		log.info("아이디 중복 체크 : " + id);
		
		return "hong/idCheck";
	}
	
}
