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
	
	/**
	 * reply 입력
	 * 
	 * @param reply
	 * @return
	 */
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
	
	/**
	 * 해당 댓글의 reply 목록 출력
	 * 
	 * @param content_nm
	 * @return
	 */
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
	
	/**
	 * reply 수정
	 * 
	 * @param reply
	 * @return
	 */
	public int modifyReply(Reply reply) {
		int cnt = -1;
		
		try {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			cnt = mapper.modifyReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * reply 삭제
	 * 
	 * @param reply
	 * @return
	 */
	public int removeReply(Reply reply) {
		int cnt = -1;
		
		try {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			cnt = mapper.removeReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

}
