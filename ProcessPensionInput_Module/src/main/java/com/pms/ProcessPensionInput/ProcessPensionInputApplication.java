package com.pms.ProcessPensionInput;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @version process-pension-service v1
 * @author Gowtham
 * @category Process Pension Microservice
 *
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ProcessPensionInputApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessPensionInputApplication.class, args);
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
