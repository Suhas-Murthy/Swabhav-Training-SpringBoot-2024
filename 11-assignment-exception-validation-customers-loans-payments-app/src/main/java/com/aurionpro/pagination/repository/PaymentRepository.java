package com.aurionpro.pagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.pagination.entity.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	Payment findByAmount(int amount);
	Page<Payment> findByAmount(int amount, Pageable pageable);
}
