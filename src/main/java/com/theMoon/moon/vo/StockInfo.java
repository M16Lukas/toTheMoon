package com.theMoon.moon.vo;

import lombok.Getter;
import lombok.Setter;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

@Getter
@Setter
public class StockInfo {
	
	private String symbol;
	private String name;
	private String exchange;
	
	private StockQuote quote = null;
	private StockStats stats = null;
	private StockDividend dividend = null;
	
	
	private double dailyReturn = 0.0;		// 전일 대비 증감
	private double fluctuationRate = 0.0;   // 전일 대비 증감률
	
	public StockInfo() {}
	
	public StockInfo(String symbol, String name, String exchange, StockQuote quote, StockStats stats, StockDividend dividend) {
		this.symbol = symbol.toUpperCase();
		this.name = name;
		this.exchange = exchange;
		this.quote = quote;
		this.stats = stats;
		this.dividend = dividend;
		
		if(quote.getPrice() != null && quote.getPreviousClose() != null) {
			// fluctuation Rate of Stock		
			dailyReturn = quote.getPrice().doubleValue() - quote.getPreviousClose().doubleValue();
			fluctuationRate = (dailyReturn / quote.getPreviousClose().doubleValue()) * 100.0;
			
			// round to the 3rd decimal place
			this.dailyReturn = Math.round(dailyReturn * 100) / 100.0;
			this.fluctuationRate = Math.round(fluctuationRate * 100) / 100.0;
		}
	}
}
