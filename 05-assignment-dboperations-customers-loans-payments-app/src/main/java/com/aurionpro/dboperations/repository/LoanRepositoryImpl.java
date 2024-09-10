package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class LoanRepositoryImpl implements LoanRepository{

	
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		TypedQuery<Loan> query = manager.createQuery("select l from Loan l",Loan.class);
		return query.getResultList();
	}

	@Override
	public Loan getLoan(int loanid) {
		// TODO Auto-generated method stub
		return manager.find(Loan.class, loanid);
	}

	@Override
	@Transactional
	public void addLoan(Loan loan) {
		manager.persist(loan);
		
	}

	@Override
	@Transactional
	public void updateLoan(Loan loan) {
		manager.merge(loan);
		
	}



}
