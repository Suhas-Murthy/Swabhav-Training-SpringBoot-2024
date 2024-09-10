package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.dto.BankDto;

public interface BankService {

	Page<BankDto> getAllBanks(int pageNumber, int pageSize);

	BankDto addBank(BankDto bankDto);

	BankDto updateBank(BankDto bankDto);

}
