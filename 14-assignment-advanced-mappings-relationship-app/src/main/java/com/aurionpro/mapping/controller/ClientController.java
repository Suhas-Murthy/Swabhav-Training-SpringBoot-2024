package com.aurionpro.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.dto.ClientDto;
import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.service.ClientService;


@RestController
@RequestMapping("/clientapp")
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public String addClient(@RequestBody ClientDto clientDto) {
		ResponseEntity.ok(clientService.addClient(clientDto));
		return "Client added Successfully";
		
	}
	
	@PutMapping("/clients")
	public String updateClient(@RequestBody ClientDto clientDto) {
		ResponseEntity.ok(clientService.updateClient(clientDto));
		return "Client updated Successfully";
	}
	
	@GetMapping("/clients")
	public ResponseEntity<Page<ClientDto>> getAllClients(@RequestParam(required = false) String companyname,  @RequestParam int pageNumber, @RequestParam int pageSize){
		
//		if(companyname!=null)
//			return ResponseEntity.ok(clientService.getAllClientsByPage(companyname, pageNumber, pageSize));
		return ResponseEntity.ok(clientService.getAllClients(pageNumber, pageSize));
		
	}
}
