package com.theMoon.moon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theMoon.moon.service.MemberService;
import com.theMoon.moon.vo.Member;


@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	/**
	 * 주소입력창에 링크를 입력하여 접근할 경우 main page호 return
	 * 
	 * @param request
	 * @return
	 */
	
	@GetMapping("/login")
	private String loginForm(HttpServletRequest request) {
		if (request.getHeader("REFERER") == null) {
			return "redirect:/";
		} else {
			return "member/login";
		}
	}
	
	@ResponseBody
	@PostMapping("/login")
	private boolean login(Member member) {
		return service.login(member);
	}
	
	@GetMapping("/logout")
	private String logout(HttpServletRequest request) {
		String referer = request.getHeader("REFERER");
		if (referer == null) {
			return "redirect:/";
		} else {
			return service.logout(referer);
		}		
	}
	
	@GetMapping("/register")
	private String registerForm() {
		return "member/register";
	}
	
	@ResponseBody
	@PostMapping("/register")
	private boolean registerMember(Member member) {
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
