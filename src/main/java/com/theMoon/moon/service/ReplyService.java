package com.theMoon.moon.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theMoon.moon.dao.ReplyDAO;
import com.theMoon.moon.vo.Reply;

@Service
public class ReplyService {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private ReplyDAO dao;
	
	/**
	 * 대댓글 등록
	 * 
	 * @param content_nm
	 * @param reply
	 * @return
	 */
	public boolean insertReply(int content_nm, String reply) {
		boolean chk = false;
		String loginEmail = (String) session.getAttribute("loginEmail");
		
		if (loginEmail != null) {
			chk = dao.insertReply(new Reply(content_nm, reply, loginEmail)) > 0 ? true : false;
		}
		
		return chk;
	}
	
	/**
	 * 대댓글 목록 출력
	 * 
	 * @param content_nm
	 * @return
	 */
	public ArrayList<Reply> getReply(int content_nm){
		ArrayList<HashMap<String, Object>> maps = dao.getReply(content_nm);

		if(maps.isEmpty()) {
			return null;
		} else {
			ArrayList<Reply> lists = new ArrayList<Reply>();
		
			for(HashMap<String, Object> map : maps) {
				lists.add(new Reply(
									Integer.parseInt(String.valueOf(map.get("REPLY_NM"))),
									(String)map.get("FIRSTNAME"), 
									(String)map.get("LASTNAME"),
									(String)map.get("REPLY"), 
									(String)map.get("REPLYER"),
									(String)map.get("REPLY_INDATE"))
				);
			}
			
			return lists;
		}
	}
	
	/**
	 * 대댓글 수정
	 * 
	 * @param reply_nm
	 * @param reply
	 * @return
	 */
	public int modifyReply(int reply_nm, String reply) {
		String replyer = (String) session.getAttribute("loginEmail");
		Reply modify = new Reply(reply_nm, replyer);
		modify.setReply(reply);
		
		return dao.modifyReply(modify);
	}
	
	
	/**
	 * 대댓글 수정
	 * 
	 * @param reply_nm
	 * @return
	 */
	public int deleteReply(int reply_nm) {
		String replyer = (String) session.getAttribute("loginEmail");
		Reply reply = new Reply(reply_nm, replyer);
		
		return dao.removeReply(reply);
	}
}
