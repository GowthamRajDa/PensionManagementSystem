package com.pms.auth.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.pms.auth.AuthApplicationTests;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AuthApplicationTests.class)
public class ControllerTest {
	static String authUrl;
	static JSONObject userJson;
	static HttpHeaders headers;
	static RestTemplate restTemplate;

	@BeforeClass
	public static void runBeforeAllTestMethods() throws Exception {
		authUrl = "http://localhost:8080/authenticate";
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		userJson = new JSONObject();
		userJson.put("username", "user1");
		userJson.put("password", "pass1");
	}

	@Test
	void login() {

		HttpEntity<String> request = new HttpEntity<String>(userJson.toString(), headers);

		String result = restTemplate.postForObject(authUrl, request, String.class);

		System.out.println(result);
		assertNotNull(result);
	}

}
