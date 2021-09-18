package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.theMoon.moon.vo.Reply;


public interface ReplyMapper {
	
	// reply 입력
	public int insertReply(Reply reply);
	
	// reply 목록 출력
	public ArrayList<HashMap<String, Object>> getReply(int content_nm);
	
	// reply 수정
	public int modifyReply(Reply reply);

	// reply 삭제
	public int removeReply(Reply reply);
}
