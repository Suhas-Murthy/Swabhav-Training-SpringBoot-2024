package com.aurionpro.bank.service;

import org.springframework.data.domain.Page;

import com.aurionpro.bank.dto.AdminDto;

public interface AdminService {
	Page<AdminDto> getAllAdmins(int pageNumber, int pageSize);

	AdminDto addAdmin(AdminDto adminDto);

	AdminDto updateAdmin(AdminDto adminDto);

	AdminDto getAdminById(int adminId);

}
