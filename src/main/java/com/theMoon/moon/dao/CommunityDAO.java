package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.theMoon.moon.vo.Community;

@Repository
public class CommunityDAO {
	
	@Autowired
	private SqlSession session;
	
	public ArrayList<HashMap<String, Object>> printContent(String symbol){
		ArrayList<HashMap<String, Object>> lists = null;
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			lists = mapper.printContent(symbol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	
	public int insertContent(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.insertContent(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int removeContent(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.removeContent(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
}
