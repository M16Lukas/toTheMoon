package com.theMoon.moon.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theMoon.moon.service.MemberService;
import com.theMoon.moon.vo.Member;


@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/login")
	private String loginForm(HttpServletRequest request) {
		if (request.getHeader("REFERER") == null) {
			return "redirect:/";
		} else {
			return "member/login";
		}
	}
	
	@ResponseBody
	@PostMapping("/loginProcess")
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
	
	@ResponseBody
	@PostMapping("/forgot-password")
	private boolean forgotPassword(String inputEmail) {
		return service.forgotPassword(inputEmail);
	}
	
	@GetMapping("/update-password")
	private String updatePasswordForm() {
		return "member/update-password";
	}
	
	@ResponseBody
	@PatchMapping("/update-password")
	private boolean updatePassword(@RequestBody String updatePassword) {
		return service.updatePassword(updatePassword);
	}
	
	@ResponseBody
	@PostMapping("/google/auth")
	private boolean googleTokenSignIn(String idtoken) throws GeneralSecurityException, IOException{
		return service.googleTokenSignIn(idtoken);
	}

}
