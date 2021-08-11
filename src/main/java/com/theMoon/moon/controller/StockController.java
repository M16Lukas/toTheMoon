package com.theMoon.moon.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.Chart;
import com.theMoon.moon.vo.FeedMessage;
import com.theMoon.moon.vo.StockInfo;


@Controller
@RequestMapping(value = "/quote")
public class StockController {

	@Autowired
	private StockService service;
	
	@RequestMapping(value = "/market")
	private String index(Model model) {
		Map<String, StockInfo> stocks = null;
		
		try {
			stocks = service.index();
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
	
	/**
	 * 
	 * Chart
	 * 
	 */
	@ResponseBody
	@GetMapping(value = "/{symbol}/chartDate")
	private List<Chart> getsummaryChart(@PathVariable String symbol){
		List<Chart> chartLists = null;
		try {
			chartLists= service.stockChart(symbol, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return chartLists;
	}
	
	@GetMapping(value = "/{symbol}/chart")
	private String getChart(@PathVariable String symbol, Model model) {
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
			return "/quote/chart";
		}
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
			info = service.searchSymbol(symbol);
			map = service.historicalDataPagingResult(symbol, period1, period2, frequency, countPerPage, p);
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
	
	@ResponseBody
	@GetMapping(value = "/{symbol}/history/download")
	private void excelDownload(HttpServletResponse response
								,@PathVariable String symbol
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period1
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period2
								,@RequestParam(name="freq", defaultValue = "1D") String freq) throws IOException {
		
		service.excelDownload(response, symbol, period1, period2, freq);
	}
}
