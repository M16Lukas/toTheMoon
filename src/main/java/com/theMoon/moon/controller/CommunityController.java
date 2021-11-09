package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theMoon.moon.service.CommunityService;
import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.Community;
import com.theMoon.moon.vo.StockInfo;

@Controller
@RequestMapping(value = "/quote/{symbol}/community")
public class CommunityController {
	@Autowired
	private StockService stockService;
	
	@Autowired
	private CommunityService commuService;
	
	/**
	 * list of comments
	 * 
	 * @param symbol
	 * @param model
	 * @return
	 */
	@GetMapping
	private String communitySymbol(@PathVariable String symbol, Model model){
		StockInfo info = null;
		ArrayList<Community> lists = null;
		try {
			info = stockService.searchSymbol(symbol);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		if (info == null) {
			return "redirect:/";
		} else {
			lists = commuService.printContent(info.getSymbol());
			
			int reactions = lists.size();
			
			model.addAttribute("info", info);
			model.addAttribute("lists", lists);
			model.addAttribute("reactions", reactions);
			return "/quote/community";
		}
	}
	
	/**
	 * Enter the comments（コメント投稿）
	 * 
	 * @param symbol
	 * @param content
	 * @return
	 */
	@PostMapping("/register")
	private String insertContent(@PathVariable String symbol, String content){
		return commuService.insertContent(symbol, content);
	}
	
	/**
	 * Modify the comments（コメント修正）
	 * 
	 * @param symbol
	 * @param nm
	 * @param newContent
	 * @return
	 */
	@PostMapping("/modify")
	private String modifyContent(@PathVariable String symbol, int nm, String newContent){
		return commuService.modifyContent(symbol, nm, newContent);
	}
	
	/**
	 * Delete the comments（コメント削除）
	 * 
	 * @param symbol
	 * @param nm
	 * @return
	 */
	@GetMapping("/remove")
	private String removeContent(@PathVariable String symbol, int nm){
		return commuService.removeContent(symbol, nm);
	}
	
	
	/**
	 * Recommendation（[そう思う]ボタン）
	 * 
	 * @param symbol
	 * @param content_nm
	 * @return
	 */
	@ResponseBody
	@GetMapping("/up")
	private boolean contentUp(@PathVariable String symbol, int content_nm) {
		return commuService.contentUp(symbol, content_nm);
	}
	
	/**
	 * Not recommendation（[そう思わない]ボタン）
	 * 
	 * @param symbol
	 * @param content_nm
	 * @return
	 */
	@ResponseBody
	@GetMapping("/down")
	private boolean contentDown(@PathVariable String symbol, int content_nm){
		return commuService.contentDown(symbol, content_nm);
	}
	
}
