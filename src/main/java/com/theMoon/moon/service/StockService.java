package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

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
	
	public List<HistoricalQuote> history(String symbol) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		
		from.add(Calendar.YEAR, -1);
		List<HistoricalQuote> lists = stock.getHistory(from, to, Interval.DAILY);
		
		// desc sort
		Collections.reverse(lists);
		
		return lists;
	}
	
}
