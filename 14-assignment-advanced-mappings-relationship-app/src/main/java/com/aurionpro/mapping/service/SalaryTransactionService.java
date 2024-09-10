package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.dto.SalaryTransactionDto;

public interface SalaryTransactionService {
	Page<SalaryTransactionDto> getAllSalaryTransactions(int pageNumber, int pageSize);

	SalaryTransactionDto addBank(SalaryTransactionDto salaryTransactionDto);

	SalaryTransactionDto updateBank(SalaryTransactionDto salaryTransactionDto);
}
