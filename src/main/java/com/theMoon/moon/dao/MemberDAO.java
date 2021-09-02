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
	public Member login(String email) {
		Member user = null;
		try {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			user = mapper.login(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	// find User By Email
	public Integer findUserByEmail(String email) {
		Integer cnt = -1;
		
		try {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			cnt = mapper.findUserByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	// update password
	public int updatePassword(Member member) {
		int cnt = -1;
		
		try {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			cnt = mapper.updatePassword(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
