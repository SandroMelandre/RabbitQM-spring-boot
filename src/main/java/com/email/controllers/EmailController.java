package com.email.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.dtos.EmailDto;
import com.email.models.EmailModel;
import com.email.services.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/send-email")
	public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDTO) {
		
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDTO, emailModel);
		emailService.sendMail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/emails")
	public List<EmailModel> getAllEmails(){
		return emailService.findAllemail();
	}

}
