package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.theMoon.moon.vo.Reply;


public interface ReplyMapper {
	
	// リプライ登録
	public int insertReply(Reply reply);
	
	// リプライ出力
	public ArrayList<HashMap<String, Object>> getReply(int content_nm);
	
	// リプライ修正
	public int modifyReply(Reply reply);

	// リプライ削除
	public int removeReply(Reply reply);
}
