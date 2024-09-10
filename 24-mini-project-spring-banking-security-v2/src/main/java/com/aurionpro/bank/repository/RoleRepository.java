package com.aurionpro.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bank.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRoleName(String role);
}
