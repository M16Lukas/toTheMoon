package com.theMoon.moon.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyStockInfo {
	private String symbol;
	private String name;
	private String exchange;
	private BigDecimal price = new BigDecimal("0.0");
	private BigDecimal change = new BigDecimal("0.0");
	private BigDecimal peg = new BigDecimal("0.0");
	private BigDecimal dividend = new BigDecimal("0.0");
	
	
	public CompanyStockInfo(String symbol, String name, String exchange, BigDecimal price, BigDecimal change, BigDecimal peg,
			BigDecimal dividend) {
		this.symbol = symbol;
		this.name = name;
		this.exchange = exchange;
		this.price = price;
		this.change = change;
		this.peg = peg;
		this.dividend = dividend;
	}
	
	
}
