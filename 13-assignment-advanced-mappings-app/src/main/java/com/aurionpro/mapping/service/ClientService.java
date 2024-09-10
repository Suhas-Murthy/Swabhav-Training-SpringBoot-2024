package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.entity.Client;

public interface ClientService {
	Page<Client> getAllClients(int pageNumber, int pageSize);

	Client addClient(Client client);

	Client updateClient(Client client);

	Page<Client> getAllClientsByPage(String companyname, int pageNumber, int pageSize);
}
