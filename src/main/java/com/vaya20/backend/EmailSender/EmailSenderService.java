package com.vaya20.backend.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	EmailSenderService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(Email email) throws MailException {
		//sendEmail
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("vayaarumdaunkm@gmail.com");
		mail.setFrom(email.getEmail());
		mail.setSubject("VAYA Website Contact Us: " + email.getTitle());
		mail.setText(
		"Name: " + email.getUsername() + "\n" + 
		"Email: " + email.getEmail() + "\n" + 
		"Phone#: " + email.getPhone() + "\n" +
		"Body: " + email.getBody());
		javaMailSender.send(mail);
	}
}
