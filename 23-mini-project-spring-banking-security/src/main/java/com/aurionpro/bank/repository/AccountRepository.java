package com.aurionpro.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bank.entity.Account;

public interface AccountRepository  extends JpaRepository<Account, Long> {
	  boolean existsByAccountNumber(long accountNumber);
}
