package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Payment> getAllPayments() {
		TypedQuery<Payment> query = manager.createQuery("select p from Payment p",Payment.class);
		
		return query.getResultList();
	}

	@Override
	public Payment getPayment(int paymentid) {
		// TODO Auto-generated method stub
		return manager.find(Payment.class, paymentid);
	}

	@Override
	@Transactional 
	public void addPayment(Payment payment) {
		manager.persist(payment);
		
	}

	@Override
	@Transactional
	public void updatePayment(Payment payment) {
		manager.merge(payment);
		
	}

}
