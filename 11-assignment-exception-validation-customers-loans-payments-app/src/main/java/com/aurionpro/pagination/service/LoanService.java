package com.aurionpro.pagination.service;

import java.util.Optional;

import com.aurionpro.pagination.dto.PageResponseDto;
import com.aurionpro.pagination.entity.Loan;

public interface LoanService {
PageResponseDto<Loan> getAllLoans(int pageNumber, int pageSize);
	
	PageResponseDto<Loan> getAllLoansPage(int interestrate, int pageSize, int pageNumber);
	
	
	
	Loan addLoan(Loan loan);

	Loan updateLoan(Loan loan);

	Optional<Loan> getLoanbyLoanid(int loanid);
	
//	List<Student> findByName(String name);
	Loan findByInterestrate(int interestrate);
}
