package com.theMoon.moon.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.theMoon.moon.vo.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession session;
	
	// Sign Up
	public int registerMember(Member member) {
		int cnt = -1;
		
		try {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			cnt = mapper.registerMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	// Login
	public Member login(Member member) {
		Member user = null;
		try {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			user = mapper.login(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
