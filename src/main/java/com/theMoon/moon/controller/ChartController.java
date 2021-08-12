package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theMoon.moon.service.ChartService;
import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.StockHistory;
import com.theMoon.moon.vo.StockInfo;


@Controller
@RequestMapping(value = "/quote/{symbol}")
public class ChartController {

	@Autowired
	private ChartService service;
	
	/**
	 * 
	 * Summary Page(index page) - Summary Chart
	 * 
	 * @param symbol
	 * @return
	 */
	@ResponseBody
	@GetMapping("/chartDate")
	private List<StockHistory> getsummaryChart(@PathVariable String symbol
												,@RequestParam(name = "period" ,defaultValue = "3M") String period){
		List<StockHistory> chartLists = null;
		try {
			chartLists= service.stockChart(symbol, period);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return chartLists;
	}
	
	
	/**
	 * 
	 * Chart Page
	 * 
	 * @param symbol
	 * @param model
	 * @return
	 */
	@GetMapping("/chart")
	private String getChart(@PathVariable String symbol, Model model) {
		StockInfo info = null;
		
		try {
			info = new StockService().searchSymbol(symbol);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
		if (info == null) {
			return "redirect:/";
		} else {
			model.addAttribute("info", info);
			return "/quote/chart";
		}
	}

}
