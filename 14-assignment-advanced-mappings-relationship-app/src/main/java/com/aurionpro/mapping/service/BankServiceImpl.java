package com.aurionpro.mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.aurionpro.mapping.dto.BankDto;
import com.aurionpro.mapping.entity.Bank;
import com.aurionpro.mapping.repository.BankRepository;

public class BankServiceImpl implements BankService{

	
	@Autowired
	private BankRepository bankRepo;
	
	public Bank toBankMapper(BankDto bankDto) {
		Bank bank = new Bank();
		bank.setBankName(bankDto.getBankName());
		bank.setBranch(bankDto.getBranch());
		bank.setIfsccode(bankDto.getIfsccode());
		return bank;
	}
	
	public BankDto toBankDtoMapper(Bank bank) {
		BankDto bankDto = new BankDto();
		bankDto.setBankId(bank.getBankId());
		bankDto.setBankName(bank.getBankName());
		bankDto.setBranch(bank.getBranch());
		bankDto.setIfsccode(bank.getIfsccode());


		return bankDto;
	}
	
	@Override
	public Page<BankDto> getAllBanks(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Bank> bankPage = bankRepo.findAll(pageable);
		return bankPage.map(this::toBankDtoMapper);
	}

	@Override
	public BankDto addBank(BankDto bankDto) {
		Bank bank = toBankMapper(bankDto);
		bankRepo.save(bank);
		return toBankDtoMapper(bank);
	}

	@Override
	public BankDto updateBank(BankDto bankDto) {
		Bank bank = new Bank();
		bank.setBankId(bankDto.getBankId());
		bank.setBankName(bankDto.getBankName());
		bank.setBranch(bankDto.getBranch());
		bank.setIfsccode(bankDto.getIfsccode());
	
		bank = bankRepo.save(bank);
		
		return toBankDtoMapper(bank);
	}

}
