package com.pms.PensionerDetials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @version pensioner-detail-service v1
 * @author Gowtham
 * @category Penison Detail Microservice
 */

@SpringBootApplication
public class PensionerDetialsApplication {

	public static void main(String[] args) {

		SpringApplication.run(PensionerDetialsApplication.class, args);
	}
	
	/**
	 * Bean for Rest Template to exchange requests
	 * @return
	 */

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
