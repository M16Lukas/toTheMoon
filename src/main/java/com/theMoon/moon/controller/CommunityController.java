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
	 * 
	 * Community(content : 댓글)
	 * 
	 */
	
	// 댓글 출력
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
	
	// 댓글 입력
	@PostMapping("/register")
	private String insertContent(@PathVariable String symbol, String content){
		return commuService.insertContent(symbol, content);
	}
	
	// 댓글 수정
	@PostMapping("/modify")
	private String modifyContent(@PathVariable String symbol, int nm, String newContent) {
		return commuService.modifyContent(symbol, nm, newContent);
	}
	
	// 댓글 삭제
	@GetMapping("/remove")
	private String removeContent(@PathVariable String symbol, int nm){
		return commuService.removeContent(symbol, nm);
	}
	
	// 추천
	@ResponseBody
	@PostMapping("/up")
	private String contentUp(@PathVariable String symbol, int content_nm, int upCnt){
		return Integer.toString(commuService.contentUp(symbol, content_nm, upCnt));
	}
	
	// 비추천
	@ResponseBody
	@PostMapping("/down")
	private String contentDown(@PathVariable String symbol, int content_nm, int downCnt){
		return Integer.toString(commuService.contentDown(symbol, content_nm, downCnt));
	}
	
}
