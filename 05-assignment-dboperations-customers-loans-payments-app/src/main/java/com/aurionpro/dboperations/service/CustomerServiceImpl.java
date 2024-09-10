package com.aurionpro.dboperations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Customer;
import com.aurionpro.dboperations.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepository.getAllCustomers();
	}
	@Override
	public Customer getCustomer(int customerid) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomer(customerid);
	}
	@Override
	public void addCustomer(Customer customer) {
		customerRepository.addCustomer(customer);
		
	}
	@Override
	public void updateCustomer(Customer customer) {
		customerRepository.updateCustomer(customer);
		
	}
	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.deleteCustomer(customer);
		
	}


}
