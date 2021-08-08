package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.theMoon.moon.vo.Reply;


public interface ReplyMapper {
	
	public int insertReply(Reply reply);
	
	public ArrayList<HashMap<String, Object>> getReply(int content_nm);
	
}
