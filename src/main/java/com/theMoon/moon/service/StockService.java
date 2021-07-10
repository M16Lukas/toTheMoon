package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.theMoon.moon.vo.CompanyStockInfo;

@Service
public class StockService {
	
	public CompanyStockInfo searchTicker(String symbol){
		Stock stock = null;
		
		try {
			stock = YahooFinance.get(symbol);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stock == null) return null;
		}
		
		String name = stock.getName();
		String exchange = stock.getStockExchange();
		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		
		CompanyStockInfo info = new CompanyStockInfo(symbol, name, exchange, price, change, peg, dividend);
		
		return info;
	}
	
}
