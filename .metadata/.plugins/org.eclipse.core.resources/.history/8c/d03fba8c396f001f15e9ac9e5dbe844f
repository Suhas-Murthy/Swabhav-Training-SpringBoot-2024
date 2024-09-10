package com.aurionpro.bank.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RegistrationDto {
	private String username;
	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,}$", 
    message = "Password must be at least 9 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character")
	private String password;
	private String role;
    private String customerFirstName;
    private String customerLastName;
    private String mobileNumber;
}
