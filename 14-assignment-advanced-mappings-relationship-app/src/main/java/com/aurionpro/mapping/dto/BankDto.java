package com.aurionpro.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankDto {
	private int bankId;
	private String bankName;
	private int branch;
	private int ifsccode;
}
