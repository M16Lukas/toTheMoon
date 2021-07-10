package com.theMoon.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.CompanyStockInfo;

@Controller
public class StockController {

	@Autowired
	private StockService service;
	
	@RequestMapping(value = "quote/search", method = RequestMethod.GET)
	private String searchTicker(String symbol, Model model) {
		CompanyStockInfo info = service.searchTicker(symbol);
		
		if (info == null) {
			return "redirect:/";
		} else {
			model.addAttribute("info", info);
			return "quote/index";
		}
	}
}
