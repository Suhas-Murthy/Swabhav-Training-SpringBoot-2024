package com.aurionpro.pagination.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor 
@Getter  
@Setter		
@ToString
@Table(name="employees")
public class Employee {
	@Column(name="employeeid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeid;
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column(name="phonenumber")
	private int phonenumber;
	@Column(name="email")
	private String email;
	@Column(name="position") 
	private String position;
	@Column(name="hiredate")
	private Date hiredate;
	@Column(name="salary")
	private double salary;
	@Column(name="bankaccountnumber")
	private int bankaccountnumber;
	@Column(name="bankifscnumber")
	private String bankifscnumber;
	@Column(name="status")
	private Status status;
	

}
