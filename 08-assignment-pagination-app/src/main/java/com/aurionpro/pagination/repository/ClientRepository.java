package com.aurionpro.pagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.pagination.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	Page<Client> findByCompanyname(String companyname, Pageable pageable);
}
