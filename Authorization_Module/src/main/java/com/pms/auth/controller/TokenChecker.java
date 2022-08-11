package com.pms.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class TokenChecker {


	@GetMapping(path = "/checkToken")
	public Boolean tokenChecker() {
		log.info("Valid Request,Provided token is a Valid Token");
		return true;
	}
}