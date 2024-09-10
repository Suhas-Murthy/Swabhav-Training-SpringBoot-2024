package com.aurionpro.batch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name="employees")
@Entity
public class Employee {
	@Id
	@Column(name = "employeeId")
	private int employeeId;
	@Column(name = "name")
	private String name;
	@Column(name = "salary")
	private int salary;
}
