package com.aurionpro.dboperations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Loan;
import com.aurionpro.dboperations.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		return loanRepository.getAllLoans();
	}

	@Override
	public Loan getLoan(int loanid) {
		// TODO Auto-generated method stub
		return loanRepository.getLoan(loanid);
	}

	@Override
	public void addLoan(Loan loan) {
		loanRepository.addLoan(loan);

	}

	@Override
	public void updateLoan(Loan loan) {
		loanRepository.updateLoan(loan);

	}

}
