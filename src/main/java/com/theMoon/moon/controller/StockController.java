package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theMoon.moon.service.CommunityService;
import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.Community;
import com.theMoon.moon.vo.StockInfo;


@Controller
@RequestMapping(value = "/quote")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@Autowired
	private CommunityService commuService;
	
	@RequestMapping(value = "")
	private String index(Model model) {
		Map<String, StockInfo> stocks = null;
		
		try {
			stocks = stockService.index();
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		model.addAttribute("stocks", stocks);
		return "/quote/index";
	}
	
	@GetMapping(value = "/{symbol}")
	private String searchSymbol(@PathVariable String symbol, Model model){
		StockInfo info = null;
		
		try {
			info = stockService.searchSymbol(symbol);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		if (info == null) {
			return "redirect:/";
		} else {
			model.addAttribute("info", info);
			return "/quote/info";
		}
	}	
	
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
	
	@GetMapping(value = "/{symbol}/history")
	private String historySymbol(Model model
								,@PathVariable String symbol
								,@RequestParam(name="countPerPage", defaultValue = "10") int countPerPage
								,@RequestParam(name="frequency", defaultValue = "1D") String frequency
								,@RequestParam(name="p", defaultValue = "1") int p){
		
		StockInfo info = null;
		Map<String, Object> map = null;
		
		try {
			info = stockService.searchSymbol(symbol);
			map = stockService.history(symbol, frequency, countPerPage, p);
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
			return "/quote/history";
		}
	}
	
	@GetMapping(value = "/{symbol}/history/download")
	private String excelDownload(HttpServletResponse response
								,@PathVariable String symbol
								,@RequestParam(name="freq", defaultValue = "1D") String freq) throws IOException {
		
		stockService.excelDownload(response, symbol, freq);
		
		return "/quote/history";
	}
}
