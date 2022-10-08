package com.hong.util.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hong.member.vo.LoginVO;

import lombok.extern.log4j.Log4j;


@Log4j
public class HongInterceptor extends HandlerInterceptorAdapter{

	// url에 대한 권한 정보를 저장하는 Map
	// Map<url, gradeNo>
	private static Map<String, Integer> authMap = new HashMap<>();
	
	// 이 클래스에서만 사용하는 변수
	private String url = null;
	// 페이지에 대한 등급 정보를 저장하는 메서드
	// 데이터를 넣는 방법 : static 초기화 블록
	static {
	// 공지사항 - 등록, 수정, 삭제 - 관리자 : 0
	authMap.put("/notice/write.do", 0);	
	authMap.put("/notice/update.do", 0);
	authMap.put("/notice/delete.do", 0);

		
	// Fitness 게시판 - 보기, 등록, 수정, 삭제 - 회원 : 1
	authMap.put("/hong/fitnessboard/list.do", 1);
	authMap.put("/hong/fitnessboard/view.do", 1);
	authMap.put("/hong/fitnessboard/write.do", 1);
	authMap.put("/hong/fitnessboard/update.do", 1);
	authMap.put("/hong/fitnessboard/delete.do", 1);
	
	// Running 게시판 - 보기, 등록, 수정, 삭제 - 회원 : 1
	authMap.put("/hong/runningboard/list.do", 1);
	authMap.put("/hong/runningboard/view.do", 1);
	authMap.put("/hong/runningboard/write.do", 1);
	authMap.put("/hong/runningboard/update.do", 1);
	authMap.put("/hong/runningboard/delete.do", 1);
	
	// Pilates 게시판 - 보기, 등록, 수정, 삭제 - 회원 : 1
	authMap.put("/hong/pilatesboard/list.do", 1);
	authMap.put("/hong/pilatesboard/view.do", 1);
	authMap.put("/hong/pilatesboard/write.do", 1);
	authMap.put("/hong/pilatesboard/update.do", 1);
	authMap.put("/hong/pilatesboard/delete.do", 1);

	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("===== hongInterceptor preHandle() =====");
		
		url = request.getServletPath();
		log.info("preHandle.url : " + url);
		
		// 로그인 객체 꺼내기
		// 로그인 정보는 session에 있다. sesstion이 안보인다. request에서 꺼낼 수 있다.
		HttpSession session = request.getSession();
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		// 권한이 없는 경우의 처리
		if(!checkAutority(vo)) {
			// 오류 페이지로 이동시킨다.
			response.sendRedirect(request.getContextPath() + "/hong/login.do");
			return false;
		}
		
		// 요청한 내용을 계속 진행
		return super.preHandle(request, response, handler);
	} // end of preHandle
	
	private boolean checkAutority(LoginVO vo) {
		// url 정보가 autoMap 있는지 확인 한다.
		// 데이터가 없으면(null이면) 권한 체크가 필요없는 페이지 요청입니다.
		Integer status = authMap.get(url);
		if (status == null) {
			log.info("권한이 필요없는 페이지 입니다.");
			return true;
		}
		// 여기서 부터는 로그인이 필요한 처리입니다. vo가 null이면 안된다.
		if (vo == null) {
			log.info("로그인이 필요합니다.");
			return false;
		}
		log.info("status : " + status);
		log.info("userStatus : " + vo.getStatus());
		
		// 권한이 없는 페이지 요청에 대한 처리
		if (status < vo.getStatus()) {
			log.info("권한이 없습니다.");
			return false;
		}
		log.info("권한이 있습니다.");
		return true;
	}
	
	
}
