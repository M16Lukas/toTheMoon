package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
		
		StockQuote quote = stock.getQuote(true);
		StockStats stats = stock.getStats();
		StockDividend dividend = stock.getDividend();
		
		return new StockInfo(symbol, name, exchange, quote, stats, dividend);
	}
	
	public List<HistoricalQuote> historicalList(String symbol, String frequency) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		Interval interval = null;
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -1);
		
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
		
		List<HistoricalQuote> lists = stock.getHistory(from, to, interval);
		// desc sort
		Collections.reverse(lists);
		
		return lists;
	}
	
	public Map<String, Object> history(String symbol, String frequency, int countPerPage, int currentPage) throws IOException{
		
		List<HistoricalQuote> lists = historicalList(symbol, frequency);
		
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
	
	
	public void excelDownload(HttpServletResponse response, String symbol, String freq) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ServletOutputStream fileout = response.getOutputStream();
		
		List<HistoricalQuote> lists = historicalList(symbol, freq);
		
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
		for(HistoricalQuote list : lists) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue(dateFormat.format(list.getDate().getTime()));
			cell = row.createCell(1);
			cell.setCellValue(list.getOpen().doubleValue());
			cell = row.createCell(2);
			cell.setCellValue(list.getHigh().doubleValue());
			cell = row.createCell(3);
			cell.setCellValue(list.getLow().doubleValue());
			cell = row.createCell(4);
			cell.setCellValue(list.getClose().doubleValue());
			cell = row.createCell(5);
			cell.setCellValue(list.getAdjClose().doubleValue());
			cell = row.createCell(6);
			cell.setCellValue(list.getVolume().doubleValue());
		}
		
		// content type & file name
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + symbol + ".xlsx");
		
		// Excel file Output
		wb.write(fileout);
		
		fileout.close();
		wb.close();
	}
}
