package com.aurionpro.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.jwt.entity.Customer;
import com.aurionpro.jwt.repository.CustomerRespository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRespository customerRepo;
	

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		
		return customerRepo.findById(customerId);
	}

}
