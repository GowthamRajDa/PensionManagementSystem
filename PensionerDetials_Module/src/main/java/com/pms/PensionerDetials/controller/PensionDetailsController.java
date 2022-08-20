package com.pms.PensionerDetials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.pms.PensionerDetials.DAO.PensionDetailsLoader;
import com.pms.PensionerDetials.Model.PensionerDetialsModel;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class PensionDetailsController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${CustomVariables.AuthentificationUrl}")
	private String authUrl ;

	@Autowired
	PensionDetailsLoader pensionDetailsLoader;

	/**
	 * 
	 * @param requestHeader
	 * @param aadharNumber
	 * @return PensionerDetialsModel as Response
	 * 
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/PensionerDetailByAadhaar/{aadharNumber}")
	public ResponseEntity<PensionerDetialsModel> GetPensionerDetails(@RequestHeader HttpHeaders requestHeader,
			@PathVariable String aadharNumber) {

		HttpHeaders Responseheader = new HttpHeaders();
		
		//If the request doesn't have Authorization Header
		if (!requestHeader.containsKey("Authorization")) {
			
			Responseheader.add("Warning", "Unauthorized Request Please add the Auth Key on Header");
			ResponseEntity<PensionerDetialsModel> response = new ResponseEntity<PensionerDetialsModel>(null,
					Responseheader, HttpStatus.UNAUTHORIZED);
			log.debug("Unauthorized Request");
			return response;

		}

		Responseheader.add("Origin", "port:8081");

		if (authKeyChecker(requestHeader)) {

			PensionerDetialsModel pensionDetails = pensionDetailsLoader.getPensionerByAadhar(aadharNumber);

			if (pensionDetails == null) {
				Responseheader.add("Warning", aadharNumber + " Not found");
				log.debug(aadharNumber+" Not found");
				return new ResponseEntity<PensionerDetialsModel>(pensionDetails, Responseheader, HttpStatus.NOT_FOUND);
			}

			Responseheader.add("Connection", "Passed");

			ResponseEntity<PensionerDetialsModel> response = new ResponseEntity<PensionerDetialsModel>(pensionDetails,
					Responseheader, HttpStatus.OK);
			log.info(aadharNumber+" AadharNumber found");
			return response;

		} else {

			Responseheader.add("Warning", "Unauthorized Request Please Check the Auth Key on Header");
			ResponseEntity<PensionerDetialsModel> response = new ResponseEntity<PensionerDetialsModel>(null,
					Responseheader, HttpStatus.UNAUTHORIZED);
			log.debug("Unauthorized Request");
			return response;
		}

	}
	
	
	/**
	 * 
	 * Request checker will check the request is authorized or Not
	 * @param requestHeader
	 * @return true when the JWT Key is valid
	 * 
	 */
	
	public boolean authKeyChecker(HttpHeaders requestHeader) {

		RequestEntity<Void> request = RequestEntity.get(authUrl).headers(requestHeader).build();
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(request, String.class);
			return Boolean.parseBoolean(response.getBody());
		}
		catch (Forbidden e) {
			log.debug(e.getResponseBodyAsString(),e);
		}
		
		return false;

	}

}
