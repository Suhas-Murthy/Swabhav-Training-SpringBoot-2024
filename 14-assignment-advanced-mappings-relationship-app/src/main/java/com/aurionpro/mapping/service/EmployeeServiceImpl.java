package com.aurionpro.mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.EmployeeDto;
import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;

	public Employee toEmployeeMapper(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setFirstname(employeeDto.getFirstname());
		employee.setLastname(employeeDto.getLastname());
		employee.setPhonenumber(employeeDto.getPhonenumber());
		employee.setEmail(employeeDto.getEmail());
		employee.setPosition(employeeDto.getPosition());
		employee.setHiredate(employeeDto.getHiredate());
		employee.setSalary(employeeDto.getSalary());
		employee.setBankaccountnumber(employeeDto.getBankaccountnumber());
		employee.setBankifscnumber(employeeDto.getBankifscnumber());
		employee.setStatus(employeeDto.getStatus());
		return employee;
	}

	public EmployeeDto toEmployeeDtoMapper(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmployeeid(employee.getEmployeeid());
		employeeDto.setFirstname(employee.getFirstname());
		employeeDto.setLastname(employee.getLastname());
		employeeDto.setPhonenumber(employee.getPhonenumber());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setPosition(employee.getPosition());
		employeeDto.setHiredate(employee.getHiredate());
		employeeDto.setSalary(employee.getSalary());
		employeeDto.setBankaccountnumber(employee.getBankaccountnumber());
		employeeDto.setBankifscnumber(employee.getBankifscnumber());
		employeeDto.setStatus(employee.getStatus());

		return employeeDto;
	}

	@Override
	public Page<EmployeeDto> getAllEmployees(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Employee> employeePage = employeeRepo.findAll(pageable);
		return employeePage.map(this::toEmployeeDtoMapper);
	}

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		Employee employee = toEmployeeMapper(employeeDto);

		employee = employeeRepo.save(employee);
		return toEmployeeDtoMapper(employee);

	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		Employee employee = toEmployeeMapper(employeeDto);
		employee.setEmployeeid(employeeDto.getEmployeeid());
		employee.setFirstname(employeeDto.getFirstname());
		employee.setLastname(employeeDto.getLastname());
		employee.setPhonenumber(employeeDto.getPhonenumber());
		employee.setEmail(employeeDto.getEmail());
		employee.setPosition(employeeDto.getPosition());
		employee.setHiredate(employeeDto.getHiredate());
		employee.setSalary(employeeDto.getSalary());
		employee.setBankaccountnumber(employeeDto.getBankaccountnumber());
		employee.setBankifscnumber(employeeDto.getBankifscnumber());
		employee.setStatus(employeeDto.getStatus());
		employee = employeeRepo.save(employee);
		
		return toEmployeeDtoMapper(employee);
	}

//	@Override
//	public Page<EmployeeDto> getAllEmploeesByPage(String firstname, int pageSize, int pageNumber) {
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		return employeeRepo.findByFirstname(firstname, pageable);
//	}

}
