package com.aurionpro.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.CustomerDto;
import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public Customer toCustomerMapper(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setCustomerFirstName(customerDto.getCustomerFirstName());
		customer.setCustomerLastName(customerDto.getCustomerLastName());
		customer.setUsername(customerDto.getUsername());
		customer.setPassword(customerDto.getPassword());
		return customer;
	}

	public CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerFirstName(customer.getCustomerFirstName());
		customerDto.setCustomerLastName(customer.getCustomerLastName());
		customerDto.setUsername(customer.getUsername());
		customerDto.setPassword(customer.getPassword());
		customerDto.setCustomerId(customer.getCustomerId());

		return customerDto;
	}

	@Override
	public Page<CustomerDto> getAllCustomers(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Customer> employeePage = customerRepo.findAll(pageable);
		return employeePage.map(this::toCustomerDtoMapper);
	}

	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer customer = toCustomerMapper(customerDto);

		customer = customerRepo.save(customer);

		return toCustomerDtoMapper(customer);
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Customer customer = toCustomerMapper(customerDto);
		customer.setCustomerId(customerDto.getCustomerId());

		customer = customerRepo.save(customer);

		return toCustomerDtoMapper(customer);
	}

	@Override
	public CustomerDto getCustomerById(int customerId) {
		Customer customer = null;
		Optional<Customer> optionalCustomer = customerRepo.findById(customerId);

		if (!optionalCustomer.isPresent()) {
			return null;
		}
		customer = optionalCustomer.get();
		return toCustomerDtoMapper(customer);
	}

}
