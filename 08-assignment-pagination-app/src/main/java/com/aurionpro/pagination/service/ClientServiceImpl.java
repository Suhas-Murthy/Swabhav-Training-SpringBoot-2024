package com.aurionpro.pagination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.pagination.entity.Client;
import com.aurionpro.pagination.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public Page<Client> getAllClients(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return clientRepo.findAll(pageable);
	}

	@Override
	public Client addClient(Client client) {
		
		return clientRepo.save(client);
	}

	@Override
	public Client updateClient(Client client) {
		// TODO Auto-generated method stub
		return clientRepo.save(client);
	}

	@Override
	public Page<Client> getAllClientsByPage(String companyname, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return clientRepo.findByCompanyname(companyname, pageable);
	}

}
