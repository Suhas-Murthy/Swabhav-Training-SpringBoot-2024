package com.aurionpro.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.entity.KYCDocuments;

public interface KYCDocumentsRepository extends JpaRepository<KYCDocuments, Integer> {

	List<KYCDocuments> findByCustomerCustomerId(int customerId);

}
