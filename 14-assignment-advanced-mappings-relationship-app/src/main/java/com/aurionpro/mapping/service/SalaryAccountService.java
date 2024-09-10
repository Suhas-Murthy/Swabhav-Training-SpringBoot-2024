package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.dto.SalaryAccountDto;

public interface SalaryAccountService {
	Page<SalaryAccountDto> getAllBanks(int pageNumber, int pageSize);

	SalaryAccountDto addBank(SalaryAccountDto salaryAccountDto);

	SalaryAccountDto updateBank(SalaryAccountDto salaryAccountDto);
}
