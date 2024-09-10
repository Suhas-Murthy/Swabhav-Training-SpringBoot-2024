package com.aurionpro.bank.service;

import org.springframework.data.domain.Page;

import com.aurionpro.bank.dto.CustomerDto;

public interface CustomerService {
	Page<CustomerDto> getAllCustomers(int pageNumber, int pageSize);

	CustomerDto addCustomer(CustomerDto customerDto);

	CustomerDto updateCustomer(CustomerDto customerDto);
	
	CustomerDto getCustomerById(int customerId);
}
