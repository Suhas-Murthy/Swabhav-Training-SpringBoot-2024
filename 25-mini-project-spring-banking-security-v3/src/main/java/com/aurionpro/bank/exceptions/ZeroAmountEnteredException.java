package com.aurionpro.bank.exceptions;

public class ZeroAmountEnteredException extends RuntimeException{
	public String getMessage() {
		return "Amount Cannot be Zero";
	}
}
