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
	 * Enter the comments（コメント投稿）
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
	 * Modify the comments（コメント修正）
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
	 * Delete the comments（コメント削除）
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
	 * Recommendation（[そう思う]ボタン）
	 * 
	 * @param symbol
	 * @param content_nm
	 * @return
	 */
	public boolean contentUp(String symbol, int content_nm) {
		boolean isValid = false;
		Community content = new Community();
		String loginEmail = (String) session.getAttribute("loginEmail");
		
		// ログインした場合
		if (loginEmail != null) {
			content.setContent_nm(content_nm);
			content.setEmail(loginEmail);
			content.setSymbol(symbol);
			
			HashMap<String, String> map = dao.checkContentLikeHistory(content);
			// ログインしたアカウントで推薦をしたヒストリーがない場合
			if(map == null) {
				// 推薦　&　ヒストリー追加
				if (dao.contentUp(content) > 0 && dao.insertContentLikeHistory(content) > 0) {
					isValid = true;
				}
			} 
			// ログインしたアカウントの推薦ヒストリーの中で該当コメントに推薦をしたヒストリーがない場合
			else {
				if(map.get("CHECK_LIKE").equals("N")) {
					// 推薦　&　ヒストリーアップデート
					if (dao.contentUp(content) > 0 && dao.updateContentLikeHistory(content) > 0) {
						isValid = true;
					}
				}
			}
		}
		
		return isValid;
	}
	
	/**
	 * Not recommendation（[そう思わない]ボタン）
	 * 
	 * @param symbol
	 * @param content_nm
	 * @return
	 */
	public boolean contentDown(String symbol, int content_nm) {
		boolean isValid = false;
		Community content = new Community();
		String loginEmail = (String) session.getAttribute("loginEmail");
		
		// ログインした場合
		if (loginEmail != null) {
			content.setContent_nm(content_nm);
			content.setEmail(loginEmail);
			content.setSymbol(symbol);
			
			HashMap<String, String> map = dao.checkContentLikeHistory(content);
			// ログインしたアカウントで非推薦をしたヒストリーがない場合
			if(map == null) {
				// 非推薦　&　ヒストリー追加
				if (dao.contentDown(content) > 0 && dao.insertContentDisLikeHistory(content) > 0) {
					isValid = true;
				}
			} 
			// ログインしたアカウントの非推薦ヒストリーの中で該当コメントに非推薦をしたヒストリーがない場合
			else {
				if(map.get("CHECK_DISLIKE").equals("N")) {
					// 非推薦　&　ヒストリーアップデート
					if (dao.contentDown(content) > 0 && dao.updateContentDisLikeHistory(content) > 0) {
						isValid = true;
					}
				}
			}
		}
		
		return isValid;
	}
}
