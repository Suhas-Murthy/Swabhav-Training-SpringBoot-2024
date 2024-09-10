package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Customer;

public interface CustomerRepository {

	List<Customer> getAllCustomers();
	Customer getCustomer(int customerid);
	void addCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(Customer customer);
}
