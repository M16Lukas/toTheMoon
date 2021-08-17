package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.FeedMessage;
import com.theMoon.moon.vo.StockInfo;


@Controller
@RequestMapping(value = "/quote")
public class StockController {

	@Autowired
	private StockService service;
	
	@GetMapping("/{symbol}")
	private String searchSymbol(@PathVariable String symbol, Model model){
		StockInfo info = null;
		List<FeedMessage> newsLists = null;
		
		try {
			info = service.searchSymbol(symbol);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		

		if (info == null) {
			return "redirect:/";
		} else {
			newsLists = service.googleNewsRSSParser(symbol);
			model.addAttribute("info", info);
			model.addAttribute("newsLists", newsLists);
			return "/quote/index";
		}
	}	
}
