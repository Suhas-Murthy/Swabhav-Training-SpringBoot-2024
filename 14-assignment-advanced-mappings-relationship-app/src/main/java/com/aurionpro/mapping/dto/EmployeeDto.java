package com.aurionpro.mapping.dto;

import java.sql.Date;

import com.aurionpro.mapping.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
	private int employeeid;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String email;
	private String position;
	private Date hiredate;
	private double salary;
	private String bankaccountnumber;
	private String bankifscnumber;
	private Status status;
}
