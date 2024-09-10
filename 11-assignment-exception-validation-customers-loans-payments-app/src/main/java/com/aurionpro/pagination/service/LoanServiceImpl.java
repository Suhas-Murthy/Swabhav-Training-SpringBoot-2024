package com.aurionpro.pagination.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.aurionpro.pagination.dto.PageResponseDto;
import com.aurionpro.pagination.entity.Loan;
import com.aurionpro.pagination.exceptions.LoanDoesNotExistsException;
import com.aurionpro.pagination.repository.LoanRepository;

public class LoanServiceImpl implements LoanService{

	private LoanRepository loanRepo;
	@Override
	public PageResponseDto<Loan> getAllLoans(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Loan> loanPage = loanRepo.findAll(pageable);
		PageResponseDto loanPageDto = new PageResponseDto();
		loanPageDto.setTotalPages(loanPage.getTotalPages());
		loanPageDto.setTotalElements(loanPage.getTotalElements());
		loanPageDto.setSize(loanPage.getSize());
		loanPageDto.setContent(loanPage.getContent());
		loanPageDto.setLastPage(loanPage.isLast());
		return 	loanPageDto;
	}

	@Override
	public PageResponseDto<Loan> getAllLoansPage(int interestrate, int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Loan> loanPage = loanRepo.findByInterestrate(interestrate, pageable);
		PageResponseDto loanPageDto = new PageResponseDto();
		loanPageDto.setTotalPages(loanPage.getTotalPages());
		loanPageDto.setTotalElements(loanPage.getTotalElements());
		loanPageDto.setSize(loanPage.getSize());
		loanPageDto.setContent(loanPage.getContent());
		loanPageDto.setLastPage(loanPage.isLast());
		return loanPageDto;
	}

	@Override
	public Loan addLoan(Loan loan) {
		// TODO Auto-generated method stub
		return loanRepo.save(loan);
	}

	@Override
	public Loan updateLoan(Loan loan) {
		// TODO Auto-generated method stub
		return loanRepo.save(loan);
	}

	@Override
	public Optional<Loan> getLoanbyLoanid(int loanid) {
		Loan loan = null;
		Optional<Loan> dbLoan= loanRepo.findById(loanid);
		if(!dbLoan.isPresent())
			throw new LoanDoesNotExistsException();
		return dbLoan;
	}

	@Override
	public Loan findByInterestrate(int interestrate) {
		// TODO Auto-generated method stub
		return loanRepo.findByInterestrate(interestrate);
	}

}
