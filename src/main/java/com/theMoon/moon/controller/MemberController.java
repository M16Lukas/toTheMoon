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
	
	/**
	 * Move to Login Page
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
	
	/**
	 * process of login
	 * 
	 * @param member
	 * @return
	 */
	@ResponseBody
	@PostMapping("/loginProcess")
	private boolean login(Member member) {
		return service.login(member);
	}
	
	/**
	 * process of logout
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	private String logout(HttpServletRequest request) {
		String referer = request.getHeader("REFERER");
		if (referer == null) {
			return "redirect:/";
		} else {
			return service.logout(referer);
		}		
	}
	
	/**
	 * Move to account register Page
	 * 
	 * @return
	 */
	@GetMapping("/register")
	private String registerForm() {
		return "member/register";
	}
	
	/**
	 * process of account register
	 * 
	 * @param member
	 * @return
	 */
	@ResponseBody
	@PostMapping("/register")
	private boolean registerMember(Member member) {
		return service.registerMember(member);
	}
	
	/**
	 * Move to forgot-password page
	 * 
	 * @return
	 */
	@GetMapping("/forgot-password")
	private String forgotPasswordForm() {
		return "member/forgot-password";
	}
	
	/**
	 * process of reset password
	 * 
	 * @param inputEmail
	 * @return
	 */
	@ResponseBody
	@PostMapping("/forgot-password")
	private boolean forgotPassword(String inputEmail) {
		return service.forgotPassword(inputEmail);
	}
	
	/**
	 * Move to update-password page
	 * 
	 * @return
	 */
	@GetMapping("/update-password")
	private String updatePasswordForm() {
		return "member/update-password";
	}
	
	/**
	 * process of update Password
	 * 
	 * @param updatePassword
	 * @return
	 */
	@ResponseBody
	@PatchMapping("/update-password")
	private boolean updatePassword(@RequestBody String updatePassword) {
		return service.updatePassword(updatePassword);
	}
	
	/**
	 * google login Authorization
	 * 
	 * @param idtoken
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	@ResponseBody
	@PostMapping("/google/auth")
	private boolean googleTokenSignIn(String idtoken) throws GeneralSecurityException, IOException{
		return service.googleTokenSignIn(idtoken);
	}

}
