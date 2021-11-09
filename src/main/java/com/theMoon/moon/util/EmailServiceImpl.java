package com.theMoon.moon.util;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.theMoon.moon.vo.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Inject
	JavaMailSender mailSender;	// root-context.xmlに設定したbean

	@Override
	public void sendMail(EmailDTO email) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			
			// 宛先
			message.addRecipient(RecipientType.TO, new InternetAddress(email.getReceiveMail()));
			
			// 差出人
			message.addFrom( new InternetAddress[] {
					new InternetAddress(email.getSenderMail(), email.getSenderName())
				}
			);
			
			// メールのタイトル
			message.setSubject(email.getSubject(), "utf-8");
			
			// メールの本文
			message.setText(email.getMessage(), "utf-8");
			
			// メール転送
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
