package com.aurionpro.pagination.service;

import java.util.Optional;

import com.aurionpro.pagination.dto.PageResponseDto;
import com.aurionpro.pagination.entity.Payment;

public interface PaymentService {
PageResponseDto<Payment> getAllPayments(int pageNumber, int pageSize);
	
	PageResponseDto<Payment> getAllPaymentsPage(int amount, int pageSize, int pageNumber);
	
	
	
	Payment addPayment(Payment payment);

	Payment updatePayment(Payment payment);

	Optional<Payment> getPaymentbyPaymentid(int paymentid);
	
//	List<Student> findByName(String name);
	Payment findByAmount(int amount);
}
