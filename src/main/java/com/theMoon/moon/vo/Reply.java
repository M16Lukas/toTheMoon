package com.theMoon.moon.vo;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Reply {
	
	private int reply_nm;
	private int content_nm;
	private String reply;
	private String replyer; // = email
	private String firstName; 
	private String lastName;
	private String reply_indate;	
	
	public Reply() {}
	
	// 생성자 : input reply
	public Reply(int content_nm, String reply, String replyer) {
		this.content_nm = content_nm;
		this.reply = reply;
		this.replyer = replyer;
	}
	
	// 생성자 : get reply
	public Reply(int reply_nm, String firstName, String lastName, String reply, String replyer, String reply_indate) {
		this.reply_nm = reply_nm;
		this.firstName = firstName;
		this.lastName = lastName;
		this.reply = reply;
		this.replyer = replyer;
		this.reply_indate = reply_indate;
	}
	
	// 생성자 : remove reply
	public Reply(int reply_nm, String replyer) {
		this.reply_nm = reply_nm;
		this.replyer = replyer;
	}
}
