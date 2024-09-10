package com.aurionpro.dboperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Customer;
import com.aurionpro.dboperations.service.CustomerService;

@RestController
@RequestMapping("/customerapp")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());	
		
	}
	
	@GetMapping("/customers/{customerid}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int customerid){
		return ResponseEntity.ok(customerService.getCustomer(customerid));
		
	}
	
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer customer) {
		
		customerService.addCustomer(customer);
		return "Customer Added";
		
	}
	
	@PostMapping("/updatecustomers")
	public String updatecustomer(@RequestBody Customer customer) {
		
		customerService.updateCustomer(customer);
		return "Customer Updated";
		
	}
	
	@DeleteMapping("/deletecustomers")
	public String deletecustomer(@RequestBody Customer customer) {
		
		customerService.deleteCustomer(customer);
		return "Customer Deleted";
		
	}
	
	
}
