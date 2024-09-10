package com.aurionpro.bank.service;

import org.springframework.data.domain.Page;

import com.aurionpro.bank.dto.TransactionDto;

public interface TransactionService {
	Page<TransactionDto> getAllTransactions(int pageNumber, int pageSize);

	TransactionDto addTransaction(TransactionDto transactionDto);

	TransactionDto updateTransaction(TransactionDto transactionDto);

	TransactionDto getTransactionById(int transactionId);

	TransactionDto credit(TransactionDto transactionDto);

	TransactionDto debit(TransactionDto transactionDto);

	TransactionDto transfer(TransactionDto transactionDto);
}
