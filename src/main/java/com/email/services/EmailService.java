package com.email.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.enums.StatusEmail;
import com.email.models.EmailModel;
import com.email.repositories.EmailRepositoryInterface;

@Service
public class EmailService {
	
	@Autowired
	EmailRepositoryInterface emailRepositoryInterface;
	
	@Autowired
	 private JavaMailSender emailSender;
	
	@SuppressWarnings("finally")
	public EmailModel sendMail(EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message  = new SimpleMailMessage();
			message.setFrom( emailModel.getEmailFrom() );
			message.setTo( emailModel.getEmailTo() );
			message.setSubject( emailModel.getSubject() );
			message.setText( emailModel.getText() );
			emailSender.send(message);
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
			System.out.printf("error", e.getMessage());
		}finally {
			return emailRepositoryInterface.save(emailModel);
		}
		
	}
	
	public List<EmailModel> findAllemail(){
		List<EmailModel> emails  = emailRepositoryInterface.findAll();
		return emails;
	}

}
