package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.theMoon.moon.vo.PageDTO;
import com.theMoon.moon.vo.StockHistory;
import com.theMoon.moon.vo.StockInfo;

@Service
public class StockService {
	
	public HashMap<String, StockInfo> index() throws IOException{
		String[] symbols = new String[] {"ES=F", "YM=F", "NQ=F", "RTY=F"};
		
		HashMap<String, StockInfo> stocks = new HashMap<String, StockInfo>();

		for(String symbol : symbols) {
			stocks.put(symbol, searchSymbol(symbol));
		}
		
		return stocks;
	}
	
	public StockInfo searchSymbol(String symbol) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		
		if (stock == null) {
			return null;
		}
		
		String name = stock.getName();
		String exchange = stock.getStockExchange();
		
		StockQuote quote = stock.getQuote(true);
		StockStats stats = stock.getStats();
		StockDividend dividend = stock.getDividend();
		
		return new StockInfo(symbol, name, exchange, quote, stats, dividend);
	}
	
	public Map<String, Object> history(String symbol, String frequency, int countPerPage, int currentPage) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		Interval interval = null;
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -1);
		
		switch (frequency) {
		case "1D":
			interval = Interval.DAILY;
			break;
		case "1W":
			interval = Interval.WEEKLY;
			break;
		case "1M":
			interval = Interval.MONTHLY;
			break;
		default:
			interval = Interval.DAILY;
			break;
		}
		
		List<HistoricalQuote> lists = stock.getHistory(from, to, interval);
		// desc sort
		Collections.reverse(lists);
		
		// paging
		PageDTO page = new PageDTO(countPerPage, currentPage, lists.size());
		
		List<StockHistory> history = new ArrayList<StockHistory>();
		for(int i = page.getStartDataIdxNum(); i <= page.getEndDataIdxNum(); i++) {
			history.add(new StockHistory(lists.get(i)));
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("history", history);
		map.put("page", page);
		
		return map;
	}
	
}
