package com.pms.auth.repo;

import org.springframework.data.repository.CrudRepository;

import com.pms.auth.models.JwtRequest;

/**
 * 
 * @author Gowtham
 *
 * Repository Interface for creating userdetails database
 *
 */

public interface AuthentificationRepository extends CrudRepository<JwtRequest, String> {
	
}
