package com.aurionpro.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.aurionpro.email.service.EmailSenderService;

import jakarta.mail.MessagingException;


@SpringBootApplication
public class Application {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		try {
			senderService.sendEmailWithAttachment(
				"swap.kul20@gmail.com",
				"Message From Suhas Murthy",
				"Good Evening Sir,\n This is the Message Body from : Suhas Murthy \n This is a simple email message using JavaMailSender Dependency and Gmail SMTP with attachment \n Thank You Sir!",
				"C:\\Users\\suhas.murthy\\Desktop/text.txt"  // Replace with the actual file path
			);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
