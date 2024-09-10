package com.aurionpro.jwt.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customers")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customerId")
	private int customerId;
	@Column(name="customerFirstName")
	private String customerFirstName;
	@Column(name="customerLastName")
	private String customerLastName;
	@Column(name="mobileNumber")
	private long mobileNumber;
	
}
