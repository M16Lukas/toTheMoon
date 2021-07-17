package com.theMoon.moon.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theMoon.moon.dao.MemberDAO;
import com.theMoon.moon.vo.Member;

@Service
public class MemberService {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private MemberDAO dao;
	
	/**
	 * Register Member
	 * 
	 * @param member
	 * @return
	 */
	public String registerMember(Member member) {
		String path = "";
		
		if (dao.registerMember(member) > 0) {
			path = "redirect:/member/login";
		} else {
			path = "redirect:" + request.getRequestURI();
		}
		
		return path;
	}
	
	/**
	 * Login
	 * 
	 * @param member
	 * @return
	 */
	public String login(Member member) {
		String path = "";
		Member loginUser = dao.login(member);
		
		if (loginUser == null) {
			path = "redirect:" + request.getRequestURI();
		} else {
			session.setAttribute("loginFirstName", loginUser.getFirstName());
			session.setAttribute("loginLastName", loginUser.getLastName());
			session.setAttribute("loginEmail", loginUser.getEmail());
			path = "redirect:/";
		}
		
		return path;
	}
	
	
	/**
	 * Logout
	 * 
	 * @return
	 */
	public String logout() {
		session.removeAttribute("loginFirstName");
		session.removeAttribute("loginLastName");
		session.removeAttribute("loginEmail");
		System.out.println("logout");
		return "redirect:/";
	}
}
