package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.theMoon.moon.vo.StockHistory;

@Service
public class ChartService {
	
	public List<StockHistory> stockChart(String symbol, String period) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();	// today
		
		List<StockHistory> history = new ArrayList<>();

		// startDate		
		switch (period) {
		case "3D":
			from.add(Calendar.DATE, -3);
			break;
		case "5D":
			from.add(Calendar.DATE, -5);
			break;
		case "1M":
			from.add(Calendar.MONTH, -1);
			break;
		case "3M":
			from.add(Calendar.MONTH, -3);
			break;
		case "1Y":
			from.add(Calendar.YEAR, -1);
			break;
		case "5Y":
			from.add(Calendar.YEAR, -5);
			break;
		}
		
		List<HistoricalQuote> lists = stock.getHistory(from, to, Interval.DAILY);
		for(HistoricalQuote list : lists) {
			history.add(new StockHistory(list));
		}
		
		return history;
	}
	
}
