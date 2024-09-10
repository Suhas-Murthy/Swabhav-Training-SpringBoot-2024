package com.aurionpro.mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.ClientDto;
import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepo;
	
	public Client toClientMapper(ClientDto clientDto) {
		Client client = new Client();
		client.setCompanyname(clientDto.getCompanyname());
		client.setRegistrationname(clientDto.getRegistrationname());
		client.setContactperson(clientDto.getContactperson());
		client.setContactemail(clientDto.getContactemail());
		client.setContactnumber(clientDto.getContactnumber());
		client.setAddress(clientDto.getAddress());
		client.setStatus(clientDto.getStatus());
		client.setCreationdate(clientDto.getCreationdate());
		client.setKycstatus(clientDto.getKycstatus());
		return client;
	}
	
	public ClientDto toClientDtoMapper(Client client) {
		ClientDto clientDto = new ClientDto();
		clientDto.setClientid(client.getClientid());
		clientDto.setCompanyname(client.getCompanyname());
		clientDto.setRegistrationname(client.getRegistrationname());
		clientDto.setContactperson(client.getContactperson());
		clientDto.setContactemail(client.getContactemail());
		clientDto.setContactnumber(client.getContactnumber());
		clientDto.setAddress(client.getAddress());
		clientDto.setStatus(client.getStatus());
		clientDto.setCreationdate(client.getCreationdate());
		clientDto.setKycstatus(client.getKycstatus());

		return clientDto;
	}
	
	@Override
	public Page<ClientDto> getAllClients(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Client> clientPage = clientRepo.findAll(pageable);
		return clientPage.map(this::toClientDtoMapper);
	}

	@Override
	public ClientDto addClient(ClientDto clientDto) {
		Client client = toClientMapper(clientDto);
		clientRepo.save(client);
		return toClientDtoMapper(client);
	}

	@Override
	public ClientDto updateClient(ClientDto clientDto) {
		Client client = new Client();
		client.setClientid(clientDto.getClientid());
		client.setCompanyname(clientDto.getCompanyname());
		client.setRegistrationname(clientDto.getRegistrationname());
		client.setContactperson(clientDto.getContactperson());
		client.setContactemail(clientDto.getContactemail());
		client.setContactnumber(clientDto.getContactnumber());
		client.setAddress(clientDto.getAddress());
		client.setStatus(clientDto.getStatus());
		client.setCreationdate(clientDto.getCreationdate());
		client.setKycstatus(clientDto.getKycstatus());
		client = clientRepo.save(client);
		
		return toClientDtoMapper(client);
		
	}

//	@Override
//	public Page<Client> getAllClientsByPage(String companyname, int pageNumber, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		return clientRepo.findByCompanyname(companyname, pageable);
//	}

}
