package com.aurionpro.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.bank.dto.CustomerDto;
import com.aurionpro.bank.entity.Customer;

public interface CustomerService {
//	List<Customer> getAllCustomers();
//
//	Optional<Customer> getCustomerById(int customerId);

	
	Page<CustomerDto> getAllCustomers(int pageNumber, int pageSize);

	CustomerDto addCustomer(CustomerDto customerDto);

	CustomerDto updateCustomer(CustomerDto customerDto);
	
	CustomerDto getCustomerById(int customerId);
}
