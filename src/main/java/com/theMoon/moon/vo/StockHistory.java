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
		this.open 		= historical.getOpen() 		== null ? BigDecimal.ZERO : historical.getOpen();
		this.low 		= historical.getLow() 		== null ? BigDecimal.ZERO : historical.getLow();
		this.high 		= historical.getHigh() 		== null ? BigDecimal.ZERO : historical.getHigh();
		this.close 		= historical.getClose() 	== null ? BigDecimal.ZERO : historical.getClose(); 
		this.adjClose 	= historical.getAdjClose()	== null ? BigDecimal.ZERO : historical.getAdjClose();
		this.volume 	= historical.getVolume() 	== null ? 0 : historical.getVolume();
		
		date = dateFormat.format(historical.getDate().getTime());
	}
    
    
}
