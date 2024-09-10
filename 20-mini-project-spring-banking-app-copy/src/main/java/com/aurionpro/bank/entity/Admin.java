package com.aurionpro.bank.entity;

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
@Table(name = "admins")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Admin {

	@Column(name = "adminId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@Column(name = "adminFirstName")
	private String adminFirstName;
	@Column(name = "adminLastName")
	private String adminLastName;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
}
