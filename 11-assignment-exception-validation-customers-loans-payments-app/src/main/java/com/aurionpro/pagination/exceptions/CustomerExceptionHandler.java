package com.aurionpro.pagination.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.pagination.errors.CustomerErrorResponse;

@ControllerAdvice
public class CustomerExceptionHandler{

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleCustomerDoesNotExistsException(
			CustomerDoesNotExistsException exception) {

		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	
	 @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleInvalidEmailFormatException(
            InvalidEmailFormatException exception) {

        CustomerErrorResponse error = new CustomerErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	 
	 @ExceptionHandler
	 public ResponseEntity<CustomerErrorResponse> handleInvalidFirstnameFormatException(
			 InvalidFirstnameFormatException exception) {
		 
		 CustomerErrorResponse error = new CustomerErrorResponse();
		 error.setStatus(HttpStatus.BAD_REQUEST.value());
		 error.setErrorMessage(exception.getMessage());
		 error.setTimestamp(System.currentTimeMillis());
		 
		 return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	 }
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {

		CustomerErrorResponse error = new CustomerErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
//		error.setErrorMessage(exception.getMessage());
		error.setErrorMessage("Something went wrong - Validation Failed");
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException exception) {
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
//		error.setErrorMessage(exception.getMessage());
		error.setErrorMessage("Enter Details in the body");
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
}
