package com.aurionpro.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentPath) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(body);

		// Add the attachment
		File file = new File(attachmentPath);
		mimeMessageHelper.addAttachment(file.getName(), file);

		mailSender.send(mimeMessage);
		System.out.println("Mail Sent Successfully with Attachment");
	}
}
