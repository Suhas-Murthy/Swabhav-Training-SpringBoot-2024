package com.aurionpro.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bank.entity.Account;
import com.aurionpro.bank.entity.Transaction;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	List<Transaction> findByAccount(Account account);
}
