package com.theMoon.moon.vo;

import lombok.Data;

@Data
public class Community {
	// seq : community_nm_seq
	private int content_nm;
	private String symbol;
	private String email;
	private String firstName;
	private String lastName;
	private String content; // clob
	private int content_up;
	private int content_down;
	private String content_indate;
	
	public Community() {}

	// insert 
	public Community(String symbol, String email, String content) {
		this.symbol = symbol;
		this.email = email;
		this.content = content.trim();
	}
	
	
	// modify 
	public Community(String symbol, String email, int content_nm, String content) {
		this.symbol = symbol;
		this.email = email;
		this.content_nm = content_nm;
		this.content = content.trim();
	}

	
	// select
	public Community(int content_nm, String symbol, String email, String firstName, String lastName, String content, int content_up, int content_down,
			String content_indate) {
		this.content_nm = content_nm;
		this.symbol = symbol;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.content = content;
		this.content_up = content_up;
		this.content_down = content_down;
		this.content_indate = content_indate;
	}
}
