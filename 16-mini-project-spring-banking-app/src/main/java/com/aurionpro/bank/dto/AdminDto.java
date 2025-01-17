package com.aurionpro.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AdminDto {
	private int adminId;
	private String adminFirstName;
	private String adminLastName;
	private String username;
	private String password;
	
}
