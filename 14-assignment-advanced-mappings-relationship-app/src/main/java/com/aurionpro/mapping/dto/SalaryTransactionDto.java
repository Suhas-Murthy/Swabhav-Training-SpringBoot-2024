package com.aurionpro.mapping.dto;

import java.sql.Date;
import com.aurionpro.mapping.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryTransactionDto {
	private int transactionId;
	private Date transactionDate;
	private double amount;
	private Status status;
}
