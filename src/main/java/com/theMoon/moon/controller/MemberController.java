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
	private HttpServletRequest request;
	
	@Autowired
	private MemberService service;
	
	private final String origin = "http://localhost:8888";
	String referer = "";
	
	@GetMapping(value = "/login")
	private String loginForm() {
		referer = (String)request.getHeader("REFERER").replace(origin,"");		
		return "member/login";
	}
	
	@PostMapping(value = "/login")
	private String login(Member member) {
		return service.login(referer, member);
	}
	
	@GetMapping(value = "/logout")
	private String logout() {
		referer = (String)request.getHeader("REFERER").replace(origin,"");
		return service.logout(referer);
	}
	
	@GetMapping(value = "/register")
	private String registerForm() {
		return "member/register";
	}
	
	@PostMapping(value = "/register")
	private String registerMember(Member member) {
		return service.registerMember(member);
	}
	
	@GetMapping(value = "/forgot-password")
	private String forgotPasswordForm() {
		return "member/forgot-password";
	}
	
	@PostMapping(value = "/forgot-password")
	private String forgotPassword() {
		return "redirect:/member/login";
	}
	
}
