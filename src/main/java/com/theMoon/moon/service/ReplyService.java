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
	
	public boolean insertReply(int content_nm, String reply) {
		boolean chk = false;
		String loginEmail = (String) session.getAttribute("loginEmail");
		
		if (loginEmail != null) {
			chk = dao.insertReply(new Reply(content_nm, reply, loginEmail)) > 0 ? true : false;
		}
		
		return chk;
	}
	
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
}
