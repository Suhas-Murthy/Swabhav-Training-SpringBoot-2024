package com.aurionpro.pagination.service;

import java.util.Optional;

import com.aurionpro.pagination.dto.PageResponseDto;
import com.aurionpro.pagination.entity.Payment;

public class PaymentServiceImpl implements PaymentService{

	@Override
	public PageResponseDto<Payment> getAllPayments(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResponseDto<Payment> getAllPaymentsPage(int amount, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment addPayment(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Payment> getPaymentbyPaymentid(int paymentid) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Payment findByAmount(int amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
