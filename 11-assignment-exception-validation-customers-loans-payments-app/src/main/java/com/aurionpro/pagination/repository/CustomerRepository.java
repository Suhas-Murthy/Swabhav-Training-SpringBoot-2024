package com.aurionpro.pagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.pagination.entity.Customer;
import java.util.List;



public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByFirstname(String firstname);
	Page<Customer> findByFirstname(String firstname , Pageable pageable);
}
