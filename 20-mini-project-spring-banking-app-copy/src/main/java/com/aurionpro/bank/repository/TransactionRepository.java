package com.aurionpro.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.bank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
