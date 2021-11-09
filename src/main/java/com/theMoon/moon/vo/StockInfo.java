package com.theMoon.moon.vo;

import lombok.Getter;
import lombok.Setter;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

@Getter
@Setter
public class StockInfo {
	
	private String symbol;		//　シンボル
	private String name;		//　名称
	private String exchange;	//　取引所
	
	/**
	 * 参考指標
	 */
	private StockQuote quote = null;
	private StockStats stats = null;
	private StockDividend dividend = null;	// 配当情報
	
	
	private double dailyReturn = 0.0;		// 前日比増減
	private double fluctuationRate = 0.0;   // 前日比増減率
	
	/**
	 * Constructor
	 */
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
