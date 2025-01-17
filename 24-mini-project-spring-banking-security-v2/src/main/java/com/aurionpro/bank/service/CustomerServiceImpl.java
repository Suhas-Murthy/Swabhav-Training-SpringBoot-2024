package com.aurionpro.bank.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.CustomerDto;
import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.entity.User;
import com.aurionpro.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {


	@Autowired
	private CustomerRepository customerRepo;

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	public Customer toCustomerMapper(CustomerDto customerDto) {

		
		Customer customer = new Customer();
        customer.setCustomerFirstName(customerDto.getCustomerFirstName());
        customer.setCustomerLastName(customerDto.getCustomerLastName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        
        User user = new User();
        user.setUsername(customerDto.getUsername());
        user.setPassword(customerDto.getPassword());
        customer.setUser(user);

        return customer;
	}

	public CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerFirstName(customer.getCustomerFirstName());
		customerDto.setCustomerLastName(customer.getCustomerLastName());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setCustomerId(customer.getCustomerId());

	    if (customer.getUser() != null) {
	        customerDto.setUsername(customer.getUser().getUsername());
	        customerDto.setPassword(customer.getUser().getPassword());
	    }
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
