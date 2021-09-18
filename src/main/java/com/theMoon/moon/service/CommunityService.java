package com.theMoon.moon.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theMoon.moon.dao.CommunityDAO;
import com.theMoon.moon.vo.Community;

@Service
public class CommunityService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CommunityDAO dao;
	
	/**
	 * list of comments
	 * 
	 * @param symbol
	 * @return
	 */
	public ArrayList<Community> printContent(String symbol){
		ArrayList<HashMap<String, Object>> maps = dao.printContent(symbol);
		ArrayList<Community> lists = new ArrayList<Community>();
		
		for(HashMap<String, Object> map : maps) {
			lists.add(new Community(Integer.parseInt(String.valueOf(map.get("CONTENT_NM")))
									,(String) map.get("SYMBOL")
									,(String) map.get("EMAIL")
									,(String) map.get("CONTENT")
									,Integer.parseInt(String.valueOf(map.get("CONTENT_UP")))
									,Integer.parseInt(String.valueOf(map.get("CONTENT_DOWN")))
									,(String) map.get("CONTENT_INDATE")
									,Integer.parseInt(String.valueOf(map.get("REPLY_CNT")))
									)
					);
		}
		
		return lists;
	}
	
	/**
	 * Enter the comments
	 * 
	 * @param symbol
	 * @param content
	 * @return
	 */
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
	
	/**
	 * Modify the comments
	 * 
	 * @param symbol
	 * @param nm
	 * @param newContent
	 * @return
	 */
	public String modifyContent(String symbol, int nm, String newContent) {
		String path = "";
		String loginEmail = (String) session.getAttribute("loginEmail");
		Community community = null;
		
		if (loginEmail == null) {
			return "redirect:/member/login";
		} else {
			community = new Community(symbol, loginEmail, nm, newContent);
		}
		
		if (dao.modifyContent(community) > 0) {
			path = "redirect:/quote/" + symbol + "/community";
		} else {
			path = "redirect:/quote/" + symbol;
		}
		
		return path;
	}
	
	/**
	 * Delete the comments
	 * 
	 * @param symbol
	 * @param nm
	 * @return
	 */
	public String removeContent(String symbol, int nm){		
		Community content = new Community();
		content.setSymbol(symbol);
		content.setContent_nm(nm);
		content.setEmail((String) session.getAttribute("loginEmail"));
		
		dao.removeContent(content);
		
		return "redirect:/quote/" + symbol + "/community";
	}
	
	/**
	 * Recommendation
	 * 
	 * @param symbol
	 * @param content_nm
	 * @return
	 */
	public boolean contentUp(String symbol, int content_nm) {
		boolean isValid = false;
		Community content = new Community();
		String loginEmail = (String) session.getAttribute("loginEmail");
		
		// 로그인을 한 경우
		if (loginEmail != null) {
			content.setContent_nm(content_nm);
			content.setEmail(loginEmail);
			content.setSymbol(symbol);
			
			HashMap<String, String> map = dao.checkContentLikeHistory(content);
			// 로그인한 계정으로 추천 이력이 없는 경우
			if(map == null) {
				// 추천 기능 수행 & 추천 이력 추가
				if (dao.contentUp(content) > 0 && dao.insertContentLikeHistory(content) > 0) {
					isValid = true;
				}
			} 
			// 로그인한 계정으로 추천 이력 중 해당 comment에 추천한 이력이 없는 경우
			else {
				if(map.get("CHECK_LIKE").equals("N")) {
					// 추천 기능 수행 & 추천 이력 추가
					if (dao.contentUp(content) > 0 && dao.updateContentLikeHistory(content) > 0) {
						isValid = true;
					}
				}
			}
		}
		
		return isValid;
	}
	
	/**
	 * Not recommendation
	 * 
	 * @param symbol
	 * @param content_nm
	 * @return
	 */
	public boolean contentDown(String symbol, int content_nm) {
		boolean isValid = false;
		Community content = new Community();
		String loginEmail = (String) session.getAttribute("loginEmail");
		
		// 로그인을 한 경우
		if (loginEmail != null) {
			content.setContent_nm(content_nm);
			content.setEmail(loginEmail);
			content.setSymbol(symbol);
			
			HashMap<String, String> map = dao.checkContentLikeHistory(content);
			// 로그인한 계정으로 추천 이력이 없는 경우
			if(map == null) {
				// 추천 기능 수행 & 추천 이력 추가
				if (dao.contentDown(content) > 0 && dao.insertContentDisLikeHistory(content) > 0) {
					isValid = true;
				}
			} 
			// 로그인한 계정으로 추천 이력 중 해당 comment에 비추천한 이력이 없는 경우
			else {
				if(map.get("CHECK_DISLIKE").equals("N")) {
					// 추천 기능 수행 & 추천 이력 추가
					if (dao.contentDown(content) > 0 && dao.updateContentDisLikeHistory(content) > 0) {
						isValid = true;
					}
				}
			}
		}
		
		return isValid;
	}
}
