package com.aurionpro.mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.aurionpro.mapping.dto.EmployeeDto;
import com.aurionpro.mapping.dto.SalaryAccountDto;
import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.repository.SalaryAccountRepository;

public class SalaryAccountServiceImpl implements SalaryAccountService{

	@Autowired
	private SalaryAccountRepository salaryAccountRepo;
	
	public SalaryAccount toSalaryAccountMapper(SalaryAccountDto salaryAccountDto) {
		SalaryAccount salaryAccount = new SalaryAccount();
		salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
			
		return salaryAccount;
	}
	
	public SalaryAccountDto toSalaryAccountDtoMapper(SalaryAccount salaryAccount) {
		SalaryAccountDto salaryAccountDto = new SalaryAccountDto();
		salaryAccountDto.setAccountNumber(salaryAccount.getAccountNumber());
		salaryAccountDto.setAccountHolderName(salaryAccount.getAccountHolderName());


		return salaryAccountDto;
	}
	@Override
	public Page<SalaryAccountDto> getAllBanks(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<SalaryAccount> salaryAccountPage = salaryAccountRepo.findAll(pageable);
		return salaryAccountPage.map(this::toSalaryAccountDtoMapper);
	}

	@Override
	public SalaryAccountDto addBank(SalaryAccountDto salaryAccountDto) {
		SalaryAccount salaryAccount = toSalaryAccountMapper(salaryAccountDto);

		salaryAccount = salaryAccountRepo.save(salaryAccount);
		return toSalaryAccountDtoMapper(salaryAccount);
	}

	@Override
	public SalaryAccountDto updateBank(SalaryAccountDto salaryAccountDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
