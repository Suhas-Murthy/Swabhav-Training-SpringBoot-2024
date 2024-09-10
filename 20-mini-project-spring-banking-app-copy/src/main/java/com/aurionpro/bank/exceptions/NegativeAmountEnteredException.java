package com.aurionpro.bank.exceptions;

public class NegativeAmountEnteredException extends RuntimeException{
	public String getMessage() {
		return "You have entered amount in Negative and Amount cannot be Negative!!!!!!!, Enter Positive Amount.....";
	}
}
