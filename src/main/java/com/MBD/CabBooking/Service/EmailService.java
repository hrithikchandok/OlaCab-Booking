package com.MBD.CabBooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class EmailService {

	@Autowired
	private JavaMailSender eMailSender;
	
	 
	public void emailSimpleMesaage(String to,String subject, String text)
	{
		  SimpleMailMessage message = new SimpleMailMessage();
		  message.setTo(to);
		  message.setSubject(subject);
		  message.setText(text);
		  
		  eMailSender.send(message);
          		  
	}
}
