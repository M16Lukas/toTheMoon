package com.theMoon.moon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login() {
		return "member/login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	private String register() {
		return "member/register";
	}
	
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	private String forgotPassword() {
		return "member/forgot-password";
	}
}
