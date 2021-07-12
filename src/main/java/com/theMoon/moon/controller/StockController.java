package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.CompanyStockInfo;

import yahoofinance.histquotes.HistoricalQuote;

@Controller
@RequestMapping(value = "/quote")
public class StockController {

	@Autowired
	private StockService service;
	
	@RequestMapping(value = "")
	private String index() {
		return "quote/index";
	}
	
	@RequestMapping(value = "/{symbol}", method = RequestMethod.GET)
	private String searchSymbol(@PathVariable String symbol, Model model){
		CompanyStockInfo info = null;
		List<HistoricalQuote> lists = null;
		
		try {
			info = service.searchTicker(symbol);
			lists = service.chart(symbol, "1D");
		} catch (IOException e) {
			return "redirect:/";
		}
		
		if (info == null) {
			return "redirect:/";
		} else {
			model.addAttribute("info", info);
			model.addAttribute("lists", lists);
			return "quote/info";
		}
	}	
}
