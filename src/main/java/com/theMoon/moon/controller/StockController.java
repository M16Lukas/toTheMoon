package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.StockInfo;


@Controller
@RequestMapping(value = "/quote")
public class StockController {

	@Autowired
	private StockService service;
	
	@RequestMapping(value = "")
	private String index(Model model) {
		Map<String, StockInfo> stocks = null;
		
		try {
			stocks = service.index();
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		model.addAttribute("stocks", stocks);
		return "/quote/index";
	}
	
	@RequestMapping(value = "/{symbol}", method = RequestMethod.GET)
	private String searchSymbol(@PathVariable String symbol, Model model){
		StockInfo info = null;
		
		try {
			info = service.searchSymbol(symbol);
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
	
	@RequestMapping(value = "/{symbol}/community", method = RequestMethod.GET)
	private String communitySymbol(@PathVariable String symbol, Model model){
		StockInfo info = null;
		
		try {
			info = service.searchSymbol(symbol);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		if (info == null) {
			return "redirect:/";
		} else {
			model.addAttribute("info", info);
			return "/quote/community";
		}
	}
	
	@RequestMapping(value = "/{symbol}/history", method = RequestMethod.GET)
	private String historySymbol(Model model
								,@PathVariable String symbol
								,@RequestParam(name="countPerPage", defaultValue = "10") int countPerPage
								,@RequestParam(name="frequency", defaultValue = "1D") String frequency
								,@RequestParam(name="p", defaultValue = "1") int p){
		
		StockInfo info = null;
		Map<String, Object> map = null;
		
		try {
			info = service.searchSymbol(symbol);
			map = service.history(symbol, frequency, countPerPage, p);
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
}
