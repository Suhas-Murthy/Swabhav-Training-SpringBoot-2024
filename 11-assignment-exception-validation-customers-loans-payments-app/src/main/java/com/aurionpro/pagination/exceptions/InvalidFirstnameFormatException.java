package com.aurionpro.pagination.exceptions;

public class InvalidFirstnameFormatException extends RuntimeException {
	public String getMessage() {
		return "First Name can be more than 2 characters or less than 21 characters";

	}
}
