package com.vaya20.backend.EmailSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class EmailSenderController {
	
	private Logger logger = LoggerFactory.getLogger(EmailSenderController.class);
	
	@Autowired
	EmailSenderService emailSenderService;
	
	EmailSenderController(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}
	
	@RequestMapping(value="/contact-us", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void contactUs(@RequestBody Email email) {
		
		//send a notification
		try{
			this.emailSenderService.sendEmail(email);
		} catch (MailException e){
			//catch error
			logger.info("Error sending email: " + e.getMessage());
		}
		 
	}
	
}
