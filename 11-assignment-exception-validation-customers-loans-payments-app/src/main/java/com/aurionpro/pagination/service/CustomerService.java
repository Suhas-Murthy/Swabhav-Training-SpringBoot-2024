package com.aurionpro.pagination.service;

import java.util.Optional;

import com.aurionpro.pagination.dto.PageResponseDto;
import com.aurionpro.pagination.entity.Customer;

public interface CustomerService {
	PageResponseDto<Customer> getAllCustomers(int pageNumber, int pageSize);
	
	PageResponseDto<Customer> getAllCustomersPage(String firstname, int pageSize, int pageNumber);
	
	
	
	Customer addCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	Optional<Customer> getCustomerbyCustomerid(int customerid);
	
//	List<Student> findByName(String name);
	Customer findByFirstname(String firstname);
}
