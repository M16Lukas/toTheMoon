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
	
	public int modifyContent(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.modifyContent(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public HashMap<String, String> checkContentLikeHistory(Community community){
		HashMap<String, String> map = null;
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			map = mapper.checkContentLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public int insertContentLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.insertContentLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int updateContentLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.updateContentLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int contentUp(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.contentUp(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int insertContentDisLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.insertContentDisLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int updateContentDisLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.updateContentDisLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int contentDown(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.contentDown(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
