package com.aurionpro.dboperations.service;

import java.util.List;

import com.aurionpro.dboperations.entity.Payment;


public interface PaymentService {
	List<Payment> getAllPayments();
	Payment getPayment(int paymentid);
	void addPayment(Payment paymnet);
	void updatePayment(Payment payment);
}
