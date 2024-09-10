package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.entity.Salary;

public interface SalaryService {
	Page<Salary> getAllSalary(int pageNumber, int pageSize);

	Salary addBank(Salary salary);

	Salary updateBank(Salary salary);
}
