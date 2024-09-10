package com.aurionpro.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aurionpro.bank.dto.TransactionDto;
import com.aurionpro.bank.entity.Account;

public interface TransactionService {
	Page<TransactionDto> getAllTransactions(int pageNumber, int pageSize);

	TransactionDto addTransaction(TransactionDto transactionDto);

	TransactionDto updateTransaction(TransactionDto transactionDto);

	TransactionDto getTransactionById(int transactionId);

	TransactionDto credit(TransactionDto transactionDto);

	TransactionDto debit(TransactionDto transactionDto);

	TransactionDto transfer(TransactionDto transactionDto);

	List<TransactionDto> getTransactionByAccountNumber(Account account);
	
}
