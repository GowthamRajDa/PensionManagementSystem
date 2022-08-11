package com.pms.auth.repo;

import org.springframework.data.repository.CrudRepository;

import com.pms.auth.models.JwtRequest;

public interface AuthentificationRepository extends CrudRepository<JwtRequest, String> {
	
}
