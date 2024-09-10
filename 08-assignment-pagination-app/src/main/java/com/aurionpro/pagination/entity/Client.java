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
@Table(name="clients")
public class Client {
	
	@Column(name="clientid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientid;
	@Column(name="companyname")
	private String companyname;
	@Column(name="registrationname")
	private String registrationname; 
	@Column(name="contactperson")
	private String contactperson;
	@Column(name="contactemail")
	private String contactemail;
	@Column(name="contactnumber")
	private int contactnumber;
	@Column(name="address")
	private String address;
	@Column(name="status")
	private Status status;
	@Column(name="creationdate")
	private Date creationdate;
	@Column(name="kycstatus")
	private KYCStatus kycstatus;

}
