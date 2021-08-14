package com.theMoon.moon.service;


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
	private MemberDAO dao;
	
	/**
	 * Register Member
	 * 
	 * @param member
	 * @return
	 */
	public boolean registerMember(Member member) {
		return dao.registerMember(member) > 0 ? true : false;
	}
	
	/**
	 * Login
	 * 
	 * @param member
	 * @return
	 */
	public boolean login(Member member) {
		boolean isValid = false;
		Member loginUser = dao.login(member);
		
		if (loginUser != null) {
			session.setAttribute("loginFirstName", loginUser.getFirstName());
			session.setAttribute("loginLastName", loginUser.getLastName());
			session.setAttribute("loginEmail", loginUser.getEmail());
			
			isValid = true;
		}
		
		return isValid;
	}
	
	
	/**
	 * Logout
	 * 
	 * @return
	 */
	public String logout(String referer) {
		session.removeAttribute("loginFirstName");
		session.removeAttribute("loginLastName");
		session.removeAttribute("loginEmail");
		return "redirect:" + referer;
	}
}
