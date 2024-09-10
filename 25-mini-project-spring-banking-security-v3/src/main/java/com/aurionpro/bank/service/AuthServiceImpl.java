package com.aurionpro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.LoginDto;
import com.aurionpro.bank.dto.RegistrationDto;
import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.entity.Role;
import com.aurionpro.bank.entity.User;
import com.aurionpro.bank.exceptions.UserApiException;
import com.aurionpro.bank.repository.CustomerRepository;
import com.aurionpro.bank.repository.RoleRepository;
import com.aurionpro.bank.repository.UserRepository;
import com.aurionpro.bank.security.JwtTokenProvider;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private CustomerRepository customerRepo;

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Override
	@Transactional
	public User register(RegistrationDto registration) {
		logger.info("Attempting to register user with username: {}", registration.getUsername());
		if (userRepo.existsByUsername(registration.getUsername())) {
			logger.warn("User registration failed: username {} already exists", registration.getUsername());
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");
		}

		User user = new User();
		user.setUsername(registration.getUsername());

		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		logger.info("User entity created for username: {}", registration.getUsername());
		List<Role> roles = new ArrayList<Role>();
		logger.info("Roles assigned to user: {}", registration.getRole());

		Role userRole = roleRepo.findByRoleName(registration.getRole()).get();
		roles.add(userRole);
		user.setRoles(roles);
		
	    // Validate customer details and throw exception if any field is missing
	    if (registration.getCustomerFirstName() == null || registration.getCustomerFirstName().trim().isEmpty() ||
	        registration.getCustomerLastName() == null || registration.getCustomerLastName().trim().isEmpty() ||
	        registration.getMobileNumber() == null || registration.getMobileNumber().trim().isEmpty()) {
	        throw new UserApiException(HttpStatus.BAD_REQUEST, "Customer information is incomplete or invalid");
	    }
		
		
		// Create Customer entity
		Customer customer = new Customer();
		customer.setCustomerFirstName(registration.getCustomerFirstName());
		customer.setCustomerLastName(registration.getCustomerLastName());
		customer.setMobileNumber(registration.getMobileNumber());

		logger.info("Customer entity created with name: {} {}", registration.getCustomerFirstName(),
				registration.getCustomerLastName());

		// Associate User with Customer
		customer.setUser(user);
		logger.info("Saving user and customer to the database...");
		// Save User (this should also save the associated Customer due to cascade
		// settings)
		userRepo.save(user);

		// Save Customer (optional depending on your cascading configuration)
		customerRepo.save(customer);
		logger.info("User and customer saved successfully: {}", registration.getUsername());
		return user;
//		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {
		logger.info("Login attempt for username: {}", loginDto.getUsername());
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			logger.info("Authentication successful for username: {}", loginDto.getUsername());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			logger.info("Token generated for username: {}", loginDto.getUsername());
			return token;
		} catch (BadCredentialsException e) {
			logger.error("Login failed for username: {}. Reason: Bad credentials", loginDto.getUsername());
			throw new UserApiException(HttpStatus.NOT_FOUND, "Username or Password is incorrect");
		} catch (Exception e) {
			logger.error("Login failed for username: {}. Unexpected error occurred: {}", loginDto.getUsername(),
					e.getMessage());
			throw new UserApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during login");
		}
	}

}
