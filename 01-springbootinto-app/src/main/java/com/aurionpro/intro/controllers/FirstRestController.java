package com.aurionpro.intro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello!!! Welcome to Spring Boot World";
	}
	

	
	@GetMapping("/bye")
	public String sayBye() {
		return "Good Bye!!! Visit Again";
	}
}
