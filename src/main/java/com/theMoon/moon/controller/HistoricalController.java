package com.theMoon.moon.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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

import com.theMoon.moon.service.HistoricalService;
import com.theMoon.moon.service.StockService;
import com.theMoon.moon.vo.StockInfo;


@Controller
@RequestMapping(value = "/quote/{symbol}/history")
public class HistoricalController {

	@Autowired
	private HistoricalService service;
	
	/**
	 * 
	 * Historical Data Page (default)
	 * 
	 * @param model
	 * @param symbol
	 * @param period1 - from
	 * @param period2 - to
	 * @param countPerPage
	 * @param frequency
	 * @param p - paging
	 * @return
	 */
	@GetMapping
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
		
		// 기간 초기 설정(default)
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
			info = new StockService().searchSymbol(symbol);
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
	
	/**
	 * 
	 * Historical Data Download (to Excel)
	 * 
	 * @param response
	 * @param symbol
	 * @param period1
	 * @param period2
	 * @param freq
	 * @throws IOException
	 */
	@ResponseBody
	@GetMapping("/download")
	private void excelDownload(HttpServletResponse response
								,@PathVariable String symbol
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period1
								,@DateTimeFormat(pattern = "yyyy-MM-dd") Date period2
								,@RequestParam(name="freq", defaultValue = "1D") String freq) throws IOException {
		
		service.excelDownload(response, symbol, period1, period2, freq);
	}
}
