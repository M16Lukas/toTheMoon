package com.theMoon.moon.vo;

import lombok.Getter;

/**
 * e-mail転送　VO
 * 
 * @author ipark
 *
 */

@Getter
public class EmailDTO {
	
	private final String senderName = "toTheMoon";						// 差出人
	private final String senderMail = "themoon.information@gmail.com";	// 差出人e-mail
	private final String subject = "Reset Password Notification";		// タイトル
	
	private String receiveMail; // 宛先　e-mail
	private String message;		// 本文
	
	public EmailDTO(){}
	
	public EmailDTO(String receiveMail, String randomPassword) {
		this.receiveMail = receiveMail;
		message = "Hello!\r\n"
				+ "You are receiving this email because we received a password reset request for your account.\r\n"
				+ "\r\n"
				+ "Reset Password : "
				+ randomPassword
				+ "\r\n\r\n"
				+ "If you did not request a password reset, no further action is required.\r\n"
				+ "\r\n"
				+ "Regards,\r\n"
				+ "toTheMoon";
	}
}
