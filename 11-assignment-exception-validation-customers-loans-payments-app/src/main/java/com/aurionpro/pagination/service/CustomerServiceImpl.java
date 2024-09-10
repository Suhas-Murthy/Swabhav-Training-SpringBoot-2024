package com.aurionpro.pagination.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.pagination.dto.PageResponseDto;
import com.aurionpro.pagination.entity.Customer;
import com.aurionpro.pagination.exceptions.CustomerDoesNotExistsException;
import com.aurionpro.pagination.exceptions.InvalidEmailFormatException;
import com.aurionpro.pagination.exceptions.InvalidFirstnameFormatException;
import com.aurionpro.pagination.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public PageResponseDto<Customer> getAllCustomers(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Customer> customerPage = customerRepo.findAll(pageable);
		PageResponseDto customerPageDto = new PageResponseDto();
		customerPageDto.setTotalPages(customerPage.getTotalPages());
		customerPageDto.setTotalElements(customerPage.getTotalElements());
		customerPageDto.setSize(customerPage.getSize());
		customerPageDto.setContent(customerPage.getContent());
		customerPageDto.setLastPage(customerPage.isLast());
		return 	customerPageDto;
	}

	@Override
	public PageResponseDto<Customer> getAllCustomersPage(String firstname, int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Customer> customerPage = customerRepo.findByFirstname(firstname, pageable);
		PageResponseDto customerPageDto = new PageResponseDto();
		customerPageDto.setTotalPages(customerPage.getTotalPages());
		customerPageDto.setTotalElements(customerPage.getTotalElements());
		customerPageDto.setSize(customerPage.getSize());
		customerPageDto.setContent(customerPage.getContent());
		customerPageDto.setLastPage(customerPage.isLast());
		return customerPageDto;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
        if (!customer.getEmail().contains("@")) {
            throw new InvalidEmailFormatException();
        }
        if (customer.getFirstname().length()<2 || customer.getFirstname().length()>21) {
        	throw new InvalidFirstnameFormatException();
        }
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerbyCustomerid(int customerid) {

		Customer customer = null;
		Optional<Customer> dbCustomer= customerRepo.findById(customerid);
		if(!dbCustomer.isPresent())
			throw new CustomerDoesNotExistsException();
		return dbCustomer;
	}

	@Override
	public Customer findByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return customerRepo.findByFirstname(firstname);
	}

}
