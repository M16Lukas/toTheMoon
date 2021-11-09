package com.theMoon.moon.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;


import lombok.Getter;
import lombok.Setter;
import yahoofinance.histquotes.HistoricalQuote;

/**
 * 時系列データ　VO
 * 
 * @author ipark
 *
 */

@Getter
@Setter
public class StockHistory {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
    private String date;
    
    private BigDecimal open;	// 始値
    private BigDecimal low;		//　安値
    private BigDecimal high;	//　高値
    private BigDecimal close;	//　前日終値
    
    private BigDecimal adjClose;
    
    private Long volume;		//　出来高

    public StockHistory() {}
    
    /**
     * Constructor : Historical Data Page
     * 
     * @param historical
     */
	public StockHistory(HistoricalQuote historical) {
		this.open 		= historical.getOpen() 		== null ? BigDecimal.ZERO : historical.getOpen();
		this.low 		= historical.getLow() 		== null ? BigDecimal.ZERO : historical.getLow();
		this.high 		= historical.getHigh() 		== null ? BigDecimal.ZERO : historical.getHigh();
		this.close 		= historical.getClose() 	== null ? BigDecimal.ZERO : historical.getClose(); 
		this.adjClose 	= historical.getAdjClose()	== null ? BigDecimal.ZERO : historical.getAdjClose();
		this.volume 	= historical.getVolume() 	== null ? 0 : historical.getVolume();
		
		this.date = dateFormat.format(historical.getDate().getTime());
	}
}
