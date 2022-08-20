package com.pms.auth.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * JWT Request Model 
 * Contains -Username and Password as parameter
 */

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class JwtRequest {
	@Id
	private String username;
	private String password;

	
}
