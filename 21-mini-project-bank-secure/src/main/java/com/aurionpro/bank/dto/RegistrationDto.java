package com.aurionpro.bank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RegistrationDto {
	private String username;
	private String password;
	private String customerFirstName;
	private String customerLastName;
	private String role;
}
