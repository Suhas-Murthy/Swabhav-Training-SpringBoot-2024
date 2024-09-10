package com.aurionpro.jwt.service;

import java.util.List;
import java.util.Optional;

import com.aurionpro.jwt.entity.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();

	Optional<Customer> getCustomerById(int customerId);
}
