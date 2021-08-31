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
	JavaMailSender mailSender;	// root-context.xml에 설정한 bean

	@Override
	public void sendMail(EmailDTO email) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			
			// 수신자
			message.addRecipient(RecipientType.TO, new InternetAddress(email.getReceiveMail()));
			
			// 발신자
			message.addFrom( new InternetAddress[] {
					new InternetAddress(email.getSenderMail(), email.getSenderName())
				}
			);
			
			// 이메일 제목
			message.setSubject(email.getSubject(), "utf-8");
			
			// 이메일 본문
			message.setText(email.getMessage(), "utf-8");
			
			// 이메일 전송
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
