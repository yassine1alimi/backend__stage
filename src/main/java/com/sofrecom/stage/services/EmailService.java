package com.sofrecom.stage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Mail;


@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(final Mail mail) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		message.setTo(mail.getTo());
		message.setFrom("sofrecom.recrutement1@gmail.com");

		emailSender.send(message);

	}
}
