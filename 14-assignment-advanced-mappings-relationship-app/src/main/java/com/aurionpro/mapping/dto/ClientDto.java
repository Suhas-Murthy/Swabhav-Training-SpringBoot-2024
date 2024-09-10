package com.aurionpro.mapping.dto;

import java.sql.Date;

import com.aurionpro.mapping.entity.KYCStatus;
import com.aurionpro.mapping.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {
	private int clientid;
	private String companyname;
	private String registrationname; 
	private String contactperson;
	private String contactemail;
	private int contactnumber;
	private String address;
	private Status status;
	private Date creationdate;
	private KYCStatus kycstatus;
}
