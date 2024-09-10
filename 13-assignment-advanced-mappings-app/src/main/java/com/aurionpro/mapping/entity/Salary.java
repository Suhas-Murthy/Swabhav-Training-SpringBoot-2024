package com.aurionpro.mapping.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "salary")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Salary {
	@Column(name = "salaryId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salaryId;
	@Column(name = "salaryMonth")
	private Date salaryMonth;
	@Column(name = "grossSalary")
	@PositiveOrZero(message = "Gross Salary cannot be negative")
	private int grossSalary;
	@Column(name = "deductions")
	@PositiveOrZero(message = "Deducted Amount cannot be negative")
	private int deductions;
	@Column(name = "netSalary")
	@PositiveOrZero(message = "Net Salary cannot be negative")
	private int netSalary;
	@Column(name = "paymentDate")
	private int paymentDate;
	@Column(name = "status")
	private Status status;

}
