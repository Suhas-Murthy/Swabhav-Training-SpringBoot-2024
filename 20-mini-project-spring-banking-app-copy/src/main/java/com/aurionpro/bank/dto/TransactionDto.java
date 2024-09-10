package com.aurionpro.bank.dto;

import java.sql.Date;

import com.aurionpro.bank.entity.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TransactionDto {
	private int transactionId;
	private double transactionAmount;
	private Date transactionDate;
	private TransactionType transactionType;
	private long senderAccountNumber;
	private long receiverAccountNumber;
}
