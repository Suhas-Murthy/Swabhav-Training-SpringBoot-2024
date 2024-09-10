package com.aurionpro.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.bank.dto.AdminDto;
import com.aurionpro.bank.entity.Admin;
import com.aurionpro.bank.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;

	public Admin toAdminMapper(AdminDto adminDto) {
		Admin admin = new Admin();
		admin.setAdminFirstName(adminDto.getAdminFirstName());
		admin.setAdminLastName(adminDto.getAdminLastName());
		admin.setUsername(adminDto.getUsername());
		admin.setPassword(adminDto.getPassword());
		return admin;
	}

	public AdminDto toAdminDtoMapper(Admin admin) {
		AdminDto adminDto = new AdminDto();
		adminDto.setAdminFirstName(admin.getAdminFirstName());
		adminDto.setAdminLastName(admin.getAdminLastName());
		adminDto.setAdminId(admin.getAdminId());
		adminDto.setUsername(admin.getUsername());
		adminDto.setPassword(admin.getPassword());
		return adminDto;

	}

	@Override
	public Page<AdminDto> getAllAdmins(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Admin> adminPage = adminRepo.findAll(pageable);
		return adminPage.map(this::toAdminDtoMapper);
	}

	@Override
	public AdminDto addAdmin(AdminDto adminDto) {
		Admin admin = toAdminMapper(adminDto);
		admin = adminRepo.save(admin);
		return toAdminDtoMapper(admin);
	}

	@Override
	public AdminDto updateAdmin(AdminDto adminDto) {
		Admin admin = toAdminMapper(adminDto);
		admin.setAdminId(adminDto.getAdminId());
		admin = adminRepo.save(admin);
		return toAdminDtoMapper(admin);
	}

	@Override
	public AdminDto getAdminById(int adminId) {
		Admin admin = null;
		Optional<Admin> optionalAdmin = adminRepo.findById(adminId);
		if(!optionalAdmin.isPresent()) {
			return null;
		}
		admin = optionalAdmin.get();
		return toAdminDtoMapper(admin);
	}

}
