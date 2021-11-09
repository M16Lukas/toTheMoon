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
		
		/**
		 * default : today
		 */
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		
		List<StockHistory> history = new ArrayList<>();

		// startDate		
		switch (period) {
		case "3D":	// 3日前
			from.add(Calendar.DATE, -3);
			break;
		case "5D":	//　5日前
			from.add(Calendar.DATE, -5);
			break;
		case "1M":	//　1か月間
			from.add(Calendar.MONTH, -1);
			break;
		case "3M":	//　3か月間
			from.add(Calendar.MONTH, -3);
			break;
		case "1Y":	//　1年前
			from.add(Calendar.YEAR, -1);
			break;
		case "5Y":	//　5年前
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
