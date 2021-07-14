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
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.theMoon.moon.vo.StockInfo;

@Service
public class StockService {
	
	private Calendar from = Calendar.getInstance();
	private Calendar to = Calendar.getInstance();
	
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
	
	public List<HistoricalQuote> chart(String symbol, String period) throws IOException{
		List<HistoricalQuote> list = null;
		
		Stock stock = YahooFinance.get(symbol);
		
		if (stock == null) {
			return null;
		}
		
		switch (period) {
		case "1D":
			from.add(Calendar.DATE, -1);
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
		default:
			break;
		}
		
		
		list = stock.getHistory(from, to, Interval.DAILY);

		
		return list;
	}
	
}
