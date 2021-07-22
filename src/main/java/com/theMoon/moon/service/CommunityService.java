package com.theMoon.moon.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.theMoon.moon.dao.CommunityDAO;
import com.theMoon.moon.vo.Community;

@Service
public class CommunityService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CommunityDAO dao;
	
	public ArrayList<Community> printContent(String symbol){
		ArrayList<HashMap<String, Object>> maps = dao.printContent(symbol);
		ArrayList<Community> lists = new ArrayList<Community>();
		
		for(HashMap<String, Object> map : maps) {
			lists.add(new Community(Integer.parseInt(String.valueOf(map.get("CONTENT_NM")))
									,(String) map.get("SYMBOL")
									,(String) map.get("EMAIL")
									,(String) map.get("FIRSTNAME")
									,(String) map.get("LASTNAME")
									,(String) map.get("CONTENT")
									,Integer.parseInt(String.valueOf(map.get("CONTENT_UP")))
									,Integer.parseInt(String.valueOf(map.get("CONTENT_DOWN")))
									,(String) map.get("CONTENT_INDATE")
									)
					);
		}
		
		return lists;
	}
	
	
	public String insertContent(String symbol, String content) {
		String path = "";
		String loginEmail = (String) session.getAttribute("loginEmail");
		
		Community community = new Community(symbol, loginEmail, content);
		
		if (dao.insertContent(community) > 0) {
			path = "redirect:/quote/" + symbol + "/community";
		} else {
			path = "redirect:/quote/" + symbol;
		}
		
		return path;
	}
	
	public String removeContent(String symbol, int nm){		
		Community content = new Community();
		content.setSymbol(symbol);
		content.setContent_nm(nm);
		content.setEmail((String) session.getAttribute("loginEmail"));
		
		dao.removeContent(content);
		
		return "redirect:/quote/" + symbol + "/community";
	}
}
