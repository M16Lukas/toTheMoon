package com.theMoon.moon.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.Data;

@Data
public class Chart {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private String year;
	private String month;
	private String day;
	private BigDecimal close;
	
	// Constructor
	public Chart(Calendar date, BigDecimal close) {
		divideDate(date);
		this.close = close == null ? BigDecimal.ZERO : close;
	}
	
	private void divideDate(Calendar date) {
		String stringDate = dateFormat.format(date.getTime());
		int indexOfFirst = stringDate.indexOf("-");
		int indexOfSecond = stringDate.indexOf("-", (indexOfFirst + 1));
		this.year = stringDate.substring(0,indexOfFirst);
		this.month = stringDate.substring(indexOfFirst + 1, indexOfSecond);
		this.day = stringDate.substring(indexOfSecond + 1);
	}
}
