package com.aurionpro.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jwt.entity.Customer;

public interface CustomerRespository extends JpaRepository<Customer, Integer>{

}
