package com.pms.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pms.auth.models.JwtRequest;
import com.pms.auth.models.JwtResponse;
import com.pms.auth.services.MyUserDetailsService;
import com.pms.auth.utilities.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	/**
	 * Description- This method creates Token When valid username and password provided
	 * @param authRequest
	 * @return ResponseEntity
	 * @throws BadCredentialsException
	 */

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authRequest) {

		String username = authRequest.getUsername();
		String password = authRequest.getPassword();

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (BadCredentialsException e) {

			log.debug("Invalid Credential, Please Enter a valid Credential", new Exception("INVALID_CREDENTIALS", e));
			String token = "INVALID_CREDENTIALS";
			return ResponseEntity.ok(new JwtResponse(token));

		}

		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		log.info("JWT Token: " + token + " has been sent as Response");

		return ResponseEntity.ok(new JwtResponse(token));
	}

}
