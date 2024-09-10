package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Payment;

public interface PaymentRepository {
	List<Payment> getAllPayments();
	Payment getPayment(int paymentid);
	void addPayment(Payment payment);
	void updatePayment(Payment payment);
}
