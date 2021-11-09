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
	 * リプライ登録
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
	 * リプライリスト出力
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
	 * リプライ修正
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
	 * リプライ削除
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
