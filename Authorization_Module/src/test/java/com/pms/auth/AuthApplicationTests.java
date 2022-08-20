package com.pms.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.pms.auth.controller.AuthController;

@ComponentScan(basePackages = "com.pms")
@EnableScheduling
@SpringBootTest
public class AuthApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplicationTests.class, args);
	}

	@Test
	@DisplayName("Checking the Demo tests")
	void demo() {
		System.out.println("Test 1 passed");
		assertTrue(true);
	}


	@Test
	void runBeforeAllTestMethods() throws Exception {
		String authUrl;
		JSONObject userJson;
		HttpHeaders headers;
		RestTemplate restTemplate;
		System.out.println("Before Method running");
		authUrl = "http://localhost:8080/authenticate";
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		userJson = new JSONObject();
		userJson.put("username", "user1");
		userJson.put("password", "pass1");
		HttpEntity<String> request = new HttpEntity<String>(userJson.toString(), headers);
		AuthController authcon=new AuthController();
		ResponseEntity<?> responseEnt= authcon.createAuthenticationToken(null);
				
		String result = restTemplate.postForObject(authUrl, request, String.class);
		System.out.println(responseEnt);
		System.out.println(result);
		assertNotNull(result);
	}

}
