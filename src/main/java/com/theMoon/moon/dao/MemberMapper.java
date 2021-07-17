package com.theMoon.moon.dao;

import com.theMoon.moon.vo.Member;

public interface MemberMapper {
	// register
	public int registerMember(Member member);
	
	// login
	public Member login(Member member);
}
