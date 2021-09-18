package com.theMoon.moon.dao;

import com.theMoon.moon.vo.Member;

public interface MemberMapper {
	// account register
	public int registerMember(Member member);
	
	// login
	public Member login(String email);
	
	// find user account By Email
	public Integer findUserByEmail(String email);
	
	// update password
	public int updatePassword(Member member);
}
