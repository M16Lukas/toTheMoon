package com.theMoon.moon.vo;


import lombok.Getter;
import lombok.Setter;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;

@Getter
@Setter
public class CompanyStockInfo {
	
	private String symbol;
	private String name;
	private String exchange;
	
	private StockQuote quote = null;
	private StockDividend dividend = null;
	
	public CompanyStockInfo(String symbol, String name, String exchange, StockQuote quote, StockDividend dividend) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.exchange = exchange;
		this.quote = quote;
		this.dividend = dividend;
	}
	
	

	
	
}
