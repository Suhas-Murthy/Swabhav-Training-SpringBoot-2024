package com.aurionpro.bank.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class BankErrorResponse {
	private int status;
	private String errorMessage;
	private long timestamp;
}
