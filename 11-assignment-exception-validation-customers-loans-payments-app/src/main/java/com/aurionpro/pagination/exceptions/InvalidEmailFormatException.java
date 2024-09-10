package com.aurionpro.pagination.exceptions;

public class InvalidEmailFormatException  extends RuntimeException {
	public String getMessage() {
		return "Enter Proper EmailID";
	}
}
