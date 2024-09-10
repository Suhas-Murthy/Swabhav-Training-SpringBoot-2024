package com.aurionpro.dboperations.service;

import java.util.List;

import com.aurionpro.dboperations.entity.Loan;

public interface LoanService {
	List<Loan> getAllLoans();
	Loan getLoan(int loanid);
	void addLoan(Loan loan);
	void updateLoan(Loan loan);
}
