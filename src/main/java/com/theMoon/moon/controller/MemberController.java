package com.theMoon.moon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.theMoon.moon.service.MemberService;
import com.theMoon.moon.vo.Member;


@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	private final String origin = "http://localhost:8888";
	String referer = "";
	
	@GetMapping("/login")
	private String loginForm(HttpServletRequest request) {
		referer = (String)request.getHeader("REFERER").replace(origin,"");		
		return "member/login";
	}
	
	@PostMapping("/login")
	private String login(Member member) {
		return service.login(referer, member);
	}
	
	@GetMapping("/logout")
	private String logout(HttpServletRequest request) {
		referer = (String)request.getHeader("REFERER").replace(origin,"");
		return service.logout(referer);
	}
	
	@GetMapping("/register")
	private String registerForm() {
		return "member/register";
	}
	
	@PostMapping("/register")
	private String registerMember(Member member) {
		return service.registerMember(member);
	}
	
	@GetMapping("/forgot-password")
	private String forgotPasswordForm() {
		return "member/forgot-password";
	}
	
	@PostMapping("/forgot-password")
	private String forgotPassword() {
		return "redirect:/member/login";
	}
	
}
