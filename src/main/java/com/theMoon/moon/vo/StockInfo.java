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
	
	public StockInfo() {}
	
	public StockInfo(String symbol, String name, String exchange, StockQuote quote, StockStats stats,
			StockDividend dividend) {
		this.symbol = symbol.toUpperCase();
		this.name = name;
		this.exchange = exchange;
		this.quote = quote;
		this.stats = stats;
		this.dividend = dividend;
	}
}
