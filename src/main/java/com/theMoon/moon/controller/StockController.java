package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theMoon.moon.service.CommunityService;
import com.theMoon.moon.service.ReplyService;
import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.Community;
import com.theMoon.moon.vo.FeedMessage;
import com.theMoon.moon.vo.Reply;
import com.theMoon.moon.vo.StockInfo;


@Controller
@RequestMapping(value = "/quote")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@Autowired
	private CommunityService commuService;
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value = "/market")
	private String index(Model model) {
		Map<String, StockInfo> stocks = null;
		
		try {
			stocks = stockService.index();
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		model.addAttribute("stocks", stocks);
		return "/quote/market";
	}
	
	@GetMapping(value = "/{symbol}")
	private String searchSymbol(@PathVariable String symbol, Model model){
		StockInfo info = null;
		List<FeedMessage> lists = null;
		
		try {
			info = stockService.searchSymbol(symbol);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		if (info == null) {
			return "redirect:/";
		} else {
			lists = stockService.googleNewsRSSParser(symbol);
			model.addAttribute("info", info);
			model.addAttribute("lists", lists);
			return "/quote/index";
		}
	}	
	
	/**
	 * 
	 * Community(content : 댓글)
	 * 
	 */
	
	@GetMapping(value = "/{symbol}/community")
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
	
	@PostMapping(value = "/{symbol}/community/register")
	private String insertContent(@PathVariable String symbol, String content){
		return commuService.insertContent(symbol, content);
	}
	
	@PostMapping(value = "/{symbol}/community/modify")
	private String modifyContent(@PathVariable String symbol, int nm, String newContent) {
		return commuService.modifyContent(symbol, nm, newContent);
	}
	
	@GetMapping(value = "/{symbol}/community/remove")
	private String removeContent(@PathVariable String symbol, int nm){
		return commuService.removeContent(symbol, nm);
	}
	
	@ResponseBody
	@PostMapping(value = "/{symbol}/community/up")
	private String contentUp(@PathVariable String symbol, int content_nm, int upCnt){
		return Integer.toString(commuService.contentUp(symbol, content_nm, upCnt));
	}
	
	@ResponseBody
	@PostMapping(value = "/{symbol}/community/down")
	private String contentDown(@PathVariable String symbol, int content_nm, int downCnt){
		return Integer.toString(commuService.contentDown(symbol, content_nm, downCnt));
	}
	
	
	/**
	 * 
	 * Community(reply : 대댓글)
	 * 
	 */
	@ResponseBody
	@GetMapping(value = "/{symbol}/community/{content_nm}")
	private ArrayList<Reply> getReply(@PathVariable String symbol, @PathVariable int content_nm) {
		return replyService.getReply(content_nm);
	}
	
	@ResponseBody
	@PostMapping(value = "/{symbol}/community/{content_nm}")
	private boolean insertReply(@PathVariable String symbol, @PathVariable int content_nm, String reply) {
		return replyService.insertReply(content_nm, reply);
	}
	
	/*
	 * 
	 * Historical Data
	 * 
	 */
	
	@GetMapping(value = "/{symbol}/history")
	private String historySymbol(Model model
								,@PathVariable String symbol
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period1
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period2
								,@RequestParam(name="countPerPage", defaultValue = "10") int countPerPage
								,@RequestParam(name="frequency", defaultValue = "1D") String frequency
								,@RequestParam(name="p", defaultValue = "1") int p){
		
		StockInfo info = null;
		Map<String, Object> map = null;
		
		Calendar cal = Calendar.getInstance();
		
		// 기간 초기 설정
		// to (today)
		if (period2 == null) {
			period2 = cal.getTime();
		}
		
		// from (1 year ago)
		if (period1 == null) {
			cal.add(Calendar.YEAR, -1);
			period1 = cal.getTime();
		}
	
						
		try {
			info = stockService.searchSymbol(symbol);
			map = stockService.history(symbol, period1, period2, frequency, countPerPage, p);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		if (info == null) {
			return "redirect:/";
		} else {
			
			model.addAttribute("info", info);
			model.addAttribute("lists", map.get("history"));
			model.addAttribute("page", map.get("page"));
			model.addAttribute("countPerPage", countPerPage);
			model.addAttribute("frequency", frequency);
			model.addAttribute("period1", period1);
			model.addAttribute("period2", period2);
			return "/quote/history";
		}
	}
	
	
	@GetMapping(value = "/{symbol}/history/download")
	private String excelDownload(HttpServletResponse response
								,@PathVariable String symbol
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period1
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period2
								,@RequestParam(name="freq", defaultValue = "1D") String freq) throws IOException {
		
		
		stockService.excelDownload(response, symbol, period1, period2, freq);
		
		return "/quote/history";
	}
}
