package com.theMoon.moon.dao;

import com.theMoon.moon.vo.Member;

public interface MemberMapper {
	// register
	public int registerMember(Member member);
	
	// login
	public Member login(Member member);
	
	// find User By Email
	public Integer findUserByEmail(String email);
	
	// update password
	public int updatePassword(Member member);
}
