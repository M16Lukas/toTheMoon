package com.theMoon.moon.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Community {
	// seq : community_nm_seq
	private int content_nm;
	private String symbol;
	private String email;
	private String content; // type : clob
	private int content_up;
	private int content_down;
	private String content_indate;
	private int reply_cnt;
	
	/**
	 * Constructor
	 */
	public Community() {}

	/**
	 * Constructor - select
	 * 
	 * @param content_nm
	 * @param symbol
	 * @param email
	 * @param content
	 * @param content_up
	 * @param content_down
	 * @param content_indate
	 * @param reply_cnt
	 */
	public Community(int content_nm, String symbol, String email, String content, int content_up, int content_down,
			String content_indate, int reply_cnt) {
		this.content_nm = content_nm;
		this.symbol = symbol;
		this.email = email;
		this.content = content;
		this.content_up = content_up;
		this.content_down = content_down;
		this.content_indate = content_indate;
		this.reply_cnt = reply_cnt;
	}
	
	/**
	 * Constructor - insert
	 *  
	 * @param symbol
	 * @param email
	 * @param content
	 */
	public Community(String symbol, String email, String content) {
		this.symbol = symbol;
		this.email = email;
		this.content = content.trim();
	}
	
	
	/**
	 * Constructor - modify
	 * 
	 * @param symbol
	 * @param email
	 * @param content_nm
	 * @param content
	 */
	public Community(String symbol, String email, int content_nm, String content) {
		this.symbol = symbol;
		this.email = email;
		this.content_nm = content_nm;
		this.content = content.trim();
	}
}
