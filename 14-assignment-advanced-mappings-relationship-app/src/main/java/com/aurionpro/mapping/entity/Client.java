package com.aurionpro.mapping.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "client",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Employee> employees;

}
