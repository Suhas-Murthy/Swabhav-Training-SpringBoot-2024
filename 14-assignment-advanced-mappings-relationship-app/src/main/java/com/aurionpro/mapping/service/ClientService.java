package com.aurionpro.mapping.service;

import org.springframework.data.domain.Page;

import com.aurionpro.mapping.dto.ClientDto;
import com.aurionpro.mapping.entity.Client;

public interface ClientService {
	Page<ClientDto> getAllClients(int pageNumber, int pageSize);

	ClientDto addClient(ClientDto clientDto);

	ClientDto updateClient(ClientDto clientDto);

//	Page<ClientDto> getAllClientsByPage(String companyname, int pageNumber, int pageSize);
}
