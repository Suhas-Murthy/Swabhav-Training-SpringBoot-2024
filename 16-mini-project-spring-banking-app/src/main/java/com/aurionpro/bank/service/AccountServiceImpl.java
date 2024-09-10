package com.aurionpro.bank.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.AccountDto;
import com.aurionpro.bank.entity.Account;
import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.repository.AccountRepository;
import com.aurionpro.bank.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private CustomerRepository customerRepo;

	public Account toAccountMapper(AccountDto accountDto) {
		Account account = new Account();
		account.setAccountBalance(accountDto.getAccountBalance());
		return account;
	}

	public AccountDto toAccountDtoMapper(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setAccountBalance(account.getAccountBalance());
		accountDto.setCustomerId(account.getCustomer().getCustomerId());
		return accountDto;
	}

	@Override
	public Page<AccountDto> getAllAccounts(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Account> accountPage = accountRepo.findAll(pageable);
		return accountPage.map(this::toAccountDtoMapper);
	}

	@Override
	public AccountDto addAccount(AccountDto accountDto) {
		long accountNumber = generateUniqueAccountNumber();
		System.out.println("acc" + accountNumber);
		accountDto.setAccountNumber(accountNumber);

		Customer customer = customerRepo.findById(accountDto.getCustomerId())
				.orElseThrow(() -> new RuntimeException("Customer Not Found"));

		Account account = toAccountMapper(accountDto);
		account.setCustomer(customer);
		account.setAccountNumber(accountNumber);

		account = accountRepo.save(account);

		account.setCustomer(customer);
		System.out.println(customer.getCustomerId());
		return toAccountDtoMapper(account);
	}

	@Override
	public AccountDto updateAccount(AccountDto accountDto) {
		Account account = toAccountMapper(accountDto);
		account.setAccountNumber(accountDto.getAccountNumber());

		account = accountRepo.save(account);

		return toAccountDtoMapper(account);
	}

	@Override
	public AccountDto getAcountById(long accountNumber) {
		Account account = null;
		Optional<Account> optionalAccount = accountRepo.findById(accountNumber);

		if (!optionalAccount.isPresent()) {
			return null;
		}
		account = optionalAccount.get();
		return toAccountDtoMapper(account);
	}

	// Method to generate a unique account number
	private long generateUniqueAccountNumber() {
		Random random = new Random();
		long accountNumber;
		do {
			accountNumber = 100000000L + random.nextLong(900000000); // Generates a 9-digit number
		} while (accountRepo.existsByAccountNumber(accountNumber));
		return accountNumber;
	}

}
