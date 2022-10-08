//package com.hong.controller;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import lombok.extern.log4j.Log4j;
//
//@ControllerAdvice
//@Log4j
//public class MyAdvice {
//	
//	@ExceptionHandler(Exception.class)
//	public String handleException(Exception ex, Model model) {
//		log.error(ex.getMessage());
//		
//		model.addAttribute("msg", "잠시 후 다시 시도해주세요.");
//		
//		return "error/error";
//	}
//	
//}
