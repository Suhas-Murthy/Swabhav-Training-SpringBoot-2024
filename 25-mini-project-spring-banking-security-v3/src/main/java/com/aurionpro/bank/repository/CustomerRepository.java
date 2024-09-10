package com.aurionpro.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bank.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
