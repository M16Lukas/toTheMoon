package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.theMoon.moon.vo.Reply;


@Repository
public class ReplyDAO {
	
	@Autowired
	private SqlSession session;
	
	public int insertReply(Reply reply) {
		int cnt = -1;
		
		try {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			cnt = mapper.insertReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public ArrayList<HashMap<String, Object>> getReply(int content_nm){
		ArrayList<HashMap<String, Object>> lists = null;
		try {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			lists = mapper.getReply(content_nm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lists;
	}

}
