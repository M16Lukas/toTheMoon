package com.theMoon.moon.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import yahoofinance.histquotes.HistoricalQuote;

@Getter
@Setter
public class StockHistory {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
    private String date;
    
    private BigDecimal open;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal close;
    
    private BigDecimal adjClose;
    
    private Long volume;

	public StockHistory(HistoricalQuote historical) {
		this.open = historical.getOpen();
		this.low = historical.getLow();
		this.high = historical.getHigh();
		this.close = historical.getClose();
		this.adjClose = historical.getAdjClose();
		this.volume = historical.getVolume();
		
		date = dateFormat.format(historical.getDate().getTime());
	}
    
    
}
