package com.theMoon.moon.vo;

import lombok.Getter;

@Getter
public class EmailDTO {
	
	private final String senderName = "toTheMoon";
	private final String senderMail = "themoon.information@gmail.com";	// 발신자 이메일
	private final String subject = "Reset Password Notification";	// 제목
	
	private String receiveMail; // 수신자 이메일
	private String message;		// 내용
	
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
