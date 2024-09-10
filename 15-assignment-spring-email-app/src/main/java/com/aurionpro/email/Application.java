package com.aurionpro.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.aurionpro.email.service.EmailSenderService;

@SpringBootApplication
public class Application {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		senderService.sendEmail("swap.kul20@gmail.com", "Message From Suhas Murthy", "Good Evening Sir,\n This is the Message Body from : Suhas Murthy \n This is a simple email message using JavaMailSender Dependency and gmail smtp \n Thank You Sir!");
	}
}
