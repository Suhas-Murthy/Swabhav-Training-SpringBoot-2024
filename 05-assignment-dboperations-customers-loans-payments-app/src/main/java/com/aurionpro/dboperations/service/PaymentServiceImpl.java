package com.aurionpro.dboperations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Payment;
import com.aurionpro.dboperations.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepository.getAllPayments();
	}

	@Override
	public Payment getPayment(int paymentid) {
		// TODO Auto-generated method stub
		return paymentRepository.getPayment(paymentid);
	}

	@Override
	public void addPayment(Payment paymnet) {
		paymentRepository.addPayment(paymnet);
		
	}

	@Override
	public void updatePayment(Payment payment) {
		paymentRepository.updatePayment(payment);
		
	}

}
