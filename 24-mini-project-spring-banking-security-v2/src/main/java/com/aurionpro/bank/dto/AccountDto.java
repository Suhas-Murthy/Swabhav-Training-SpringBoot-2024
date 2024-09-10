package com.aurionpro.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AccountDto {
	private long accountNumber;
	private double accountBalance;
	private int customerId;
}
