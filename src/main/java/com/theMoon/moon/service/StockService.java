package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.theMoon.moon.util.RSSFeedParser;
import com.theMoon.moon.vo.Feed;
import com.theMoon.moon.vo.FeedMessage;
import com.theMoon.moon.vo.StockInfo;

@Service
public class StockService {

	/**
	 * 株式検索 (search stock info by Symbol)
	 * 
	 * @param symbol
	 * @return
	 * @throws IOException
	 */
	public StockInfo searchSymbol(String symbol) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		
		if (stock == null) {
			return null;
		}
		
		String name = stock.getName();
		String exchange = stock.getStockExchange();
		
		StockQuote quote = stock.getQuote();
		StockStats stats = stock.getStats();
		StockDividend dividend = stock.getDividend();
		
		return new StockInfo(symbol, name, exchange, quote, stats, dividend);
	}
	
	/**
	 * crawling google news about stock
	 * 
	 * @param symbol
	 * @return
	 */
	public List<FeedMessage> googleNewsRSSParser(String symbol) {
		RSSFeedParser parser = new RSSFeedParser("https://news.google.com/rss/search?q=" + symbol +"&hl=en-US&gl=US&ceid=US%3Aen");
		Feed feed = parser.readFeed();

		return feed.getMessages();
	}
	
}
