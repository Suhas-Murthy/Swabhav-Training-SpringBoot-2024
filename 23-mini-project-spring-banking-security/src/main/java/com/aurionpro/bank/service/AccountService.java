package com.aurionpro.bank.service;

import org.springframework.data.domain.Page;

import com.aurionpro.bank.dto.AccountDto;

public interface AccountService {
	Page<AccountDto> getAllAccounts(int pageNumber, int pageSize);

	AccountDto addAccount(AccountDto accountDto);

	AccountDto updateAccount(AccountDto accountDto);
	
	AccountDto getAcountById(long accountNumber);
}
