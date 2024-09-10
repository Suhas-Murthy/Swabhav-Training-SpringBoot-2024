package com.aurionpro.pagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.pagination.entity.Loan;


public interface LoanRepository extends JpaRepository<Loan, Integer>{
	Loan findByInterestrate(int interestrate);
	Page<Loan> findByInterestrate(int interestrate, Pageable pageable);
}
