package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.theMoon.moon.util.RSSFeedParser;
import com.theMoon.moon.vo.Chart;
import com.theMoon.moon.vo.Feed;
import com.theMoon.moon.vo.FeedMessage;
import com.theMoon.moon.vo.PageDTO;
import com.theMoon.moon.vo.StockHistory;
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
		
		StockQuote quote = stock.getQuote();
		StockStats stats = stock.getStats();
		StockDividend dividend = stock.getDividend();
		
		return new StockInfo(symbol, name, exchange, quote, stats, dividend);
	}
	
	public List<HistoricalQuote> historicalList(String symbol, Date period1, Date period2, String frequency) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		Interval interval = null;
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		
		// startdate
		// 검색 기간을 설정한 경우
		if (period1 != null) {
			from.setTime(period1);
		} else {
			from.add(Calendar.YEAR, -1);
		}
		
		// EndDate
		// 검색 기간을 설정한 경우
		if (period2 != null) {
			to.setTime(period2);
		}
		
		switch (frequency) {
		case "1D":
			interval = Interval.DAILY;
			break;
		case "1W":
			interval = Interval.WEEKLY;
			break;
		case "1M":
			interval = Interval.MONTHLY;
			break;
		default:
			interval = Interval.DAILY;
			break;
		}
		
		return stock.getHistory(from, to, interval);
	}
	
	public List<Chart> stockChart(String symbol, String period) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();	// today
		
		List<Chart> history = new ArrayList<>();
		
		if(period == null) { period = ""; } 
		
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
		default:
			from.add(Calendar.MONTH, -3);
			break;
		}
		
		List<HistoricalQuote> lists = stock.getHistory(from, to, Interval.DAILY);
		for(HistoricalQuote list : lists) {
			history.add(new Chart(list.getDate(), list.getClose()));
		}
		
		return history;
	}
	
	public Map<String, Object> historicalDataPagingResult(String symbol, Date period1, Date period2, String frequency, int countPerPage, int currentPage) throws IOException{
		
		List<HistoricalQuote> lists = historicalList(symbol, period1, period2, frequency);
		// desc sort
		Collections.reverse(lists);
		
		// paging
		PageDTO page = new PageDTO(countPerPage, currentPage, lists.size());
		
		List<StockHistory> history = new ArrayList<StockHistory>();
		
		for(int i = page.getStartDataIdxNum(); i <= page.getEndDataIdxNum(); i++) {
			history.add(new StockHistory(lists.get(i)));
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("history", history);
		map.put("page", page);
		
		return map;
	}
	
	
	public void excelDownload(HttpServletResponse response, String symbol, Date period1, Date period2, String freq) throws IOException {
		ServletOutputStream fileout = response.getOutputStream();
		
		List<HistoricalQuote> lists = historicalList(symbol, period1, period2, freq);
		// desc sort
		Collections.reverse(lists);
		
		List<StockHistory> history = new ArrayList<StockHistory>();
		
		for(HistoricalQuote list : lists) {
			history.add(new StockHistory(list));
		}
		
		Workbook wb = new XSSFWorkbook(); // ect : xls
		
		Sheet sheet = wb.createSheet(symbol);
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		// Header
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("Date");
		cell = row.createCell(1);
		cell.setCellValue("Open");
		cell = row.createCell(2);
		cell.setCellValue("High");
		cell = row.createCell(3);
		cell.setCellValue("Low");
		cell = row.createCell(4);
		cell.setCellValue("Close");
		cell = row.createCell(5);
		cell.setCellValue("Adj Close");
		cell = row.createCell(6);
		cell.setCellValue("Volume");
		
		// body
		for(StockHistory hist : history) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue(hist.getDate());
			cell = row.createCell(1);
			cell.setCellValue(hist.getOpen().doubleValue());
			cell = row.createCell(2);
			cell.setCellValue(hist.getHigh().doubleValue());
			cell = row.createCell(3);
			cell.setCellValue(hist.getLow().doubleValue());
			cell = row.createCell(4);
			cell.setCellValue(hist.getClose().doubleValue());
			cell = row.createCell(5);
			cell.setCellValue(hist.getAdjClose().doubleValue());
			cell = row.createCell(6);
			cell.setCellValue(hist.getVolume().doubleValue());
		}
		
		// content type & file name
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + symbol + ".xlsx");
		
		// Excel file Output
		wb.write(fileout);
		
		fileout.close();
		wb.close();
	}
	
	public List<FeedMessage> googleNewsRSSParser(String symbol) {
		RSSFeedParser parser = new RSSFeedParser("https://news.google.com/rss/search?q=" + symbol +"&hl=en-US&gl=US&ceid=US%3Aen");
		Feed feed = parser.readFeed();

		return feed.getMessages();
	}
	
}
