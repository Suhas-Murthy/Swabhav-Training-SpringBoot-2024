package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Loan;


public interface LoanRepository {
	List<Loan> getAllLoans();
	Loan getLoan(int loanid);
	void addLoan(Loan loan);
	void updateLoan(Loan loan);
}
