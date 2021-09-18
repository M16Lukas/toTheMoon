package com.theMoon.moon.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

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

import com.theMoon.moon.vo.PageDTO;
import com.theMoon.moon.vo.StockHistory;

@Service
public class HistoricalService {
	
	/**
	 * List of Historical Data
	 * 
	 * @param symbol
	 * @param period1 - from
	 * @param period2 - to
	 * @param frequency
	 * @return
	 * @throws IOException
	 */
	public List<HistoricalQuote> historicalList(String symbol, Date period1, Date period2, String frequency) throws IOException{
		Stock stock = YahooFinance.get(symbol);
		Interval interval = null;
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		
		// start date
		// 검색 기간을 설정한 경우
		if (period1 != null) {
			from.setTime(period1);
		} else {
			from.add(Calendar.YEAR, -1);
		}
		
		// end date
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

	/**
	 * Paging results about List of Historical Data
	 *  
	 * @param symbol
	 * @param period1 - from
	 * @param period2 - to
	 * @param frequency
	 * @param countPerPage
	 * @param currentPage
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> historicalDataPagingResult(String symbol, Date period1, Date period2, String frequency, int countPerPage, int currentPage) throws IOException{
		// get list of Historical Data
		List<HistoricalQuote> lists = historicalList(symbol, period1, period2, frequency);
		// sort : desc
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
	
	/**
	 * Historical Data Download (to Excel)
	 * 
	 * @param response
	 * @param symbol
	 * @param period1 - from
	 * @param period2 - to
	 * @param freq
	 * @throws IOException
	 */
	public void excelDownload(HttpServletResponse response, String symbol, Date period1, Date period2, String freq) throws IOException {
		ServletOutputStream fileout = response.getOutputStream();
		// get list of Historical Data
		List<HistoricalQuote> lists = historicalList(symbol, period1, period2, freq);
		// sort : desc
		Collections.reverse(lists);
		// paging
		List<StockHistory> history = new ArrayList<StockHistory>();
		
		for(HistoricalQuote list : lists) {
			history.add(new StockHistory(list));
		}
		
		Workbook wb = new XSSFWorkbook(); // ect : xls
		
		// Create Sheet
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
		
		// set content type & file name
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + symbol + ".xlsx");
		
		// Excel file Output
		wb.write(fileout);
		
		fileout.close();
		wb.close();
	}
	
}
