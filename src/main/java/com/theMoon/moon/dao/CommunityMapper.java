package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.theMoon.moon.vo.Community;

public interface CommunityMapper {
	public ArrayList<HashMap<String, Object>> printContent(String symbol);
	
	public int insertContent(Community community);
	
	public int removeContent(Community community);
	
	public int contentUp(Community community);

	public int contentDown(Community community);
}
