package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> query = manager.createQuery("select c from Customer c",Customer.class);				
		return query.getResultList();
	}

	@Override
	public Customer getCustomer(int customerid) {
		// TODO Auto-generated method stub
		return manager.find(Customer.class, customerid);
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		manager.persist(customer);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		manager.merge(customer);
		
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		if(!manager.contains(customer)) {
			customer=manager.merge(customer);
		}
		manager.remove(customer);
		
	}
	
	
	

}
