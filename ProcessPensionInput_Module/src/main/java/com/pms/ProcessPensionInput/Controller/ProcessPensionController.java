package com.pms.ProcessPensionInput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pms.ProcessPensionInput.Models.PensionerDetialsModel;
import com.pms.ProcessPensionInput.Models.ProcessedPensionModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProcessPensionController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ProcessedPensionModel processedPensionModel;

	@Value("${CustomVariables.PensionDetailsURL}")
	private String pensionDetailsURL;

	/**
	 * This Method will return the Process pension when Aadhar number provider
	 * 
	 * @param requestHeader
	 * @param aadharNumber
	 * @return ProcessedPensionModel
	 * 
	 */

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/ProcessPension/{aadharNumber}")
	public ResponseEntity<ProcessedPensionModel> ProcessPension(@RequestHeader HttpHeaders requestHeader,
			@PathVariable String aadharNumber) {

		PensionerDetialsModel pensionDetails = GetDetailByAadhar(requestHeader, aadharNumber);

		HttpHeaders Responseheader = new HttpHeaders();

		if (pensionDetails == null) {
			ResponseEntity<ProcessedPensionModel> response = new ResponseEntity<ProcessedPensionModel>(null,
					Responseheader, HttpStatus.NOT_FOUND);
			return response;
		}

		String aadhar = pensionDetails.getAadharNumber();

		if (aadharNumber.equals(aadhar)) {
			Long salary = pensionDetails.getSalaryEarned();
			Long allowance = pensionDetails.getAllowances();
			String classification = pensionDetails.getClassifcation();
			String bankType = pensionDetails.getBanktype();

			processedPensionModel.setAadharNumber(aadhar);
			processedPensionModel.setPensionAmount(calculatePension(classification, salary, allowance));
			processedPensionModel.setServiceCharge(calculateServiceCharge(bankType));
			ResponseEntity<ProcessedPensionModel> response = new ResponseEntity<ProcessedPensionModel>(
					processedPensionModel, Responseheader, HttpStatus.OK);
			log.info("Aadharcard found: " + processedPensionModel);

			return response;
		} else {
			log.info("Aadharcard Notfound: " + processedPensionModel);

			return null;
		}
	}

	/**
	 * This method will act as Service layer which fetch the aadhar details using
	 * aadharnumber
	 * 
	 * @param header
	 * @param aadharNumber
	 * @return PensionerDetialsModel
	 */

	public PensionerDetialsModel GetDetailByAadhar(HttpHeaders header, String aadharNumber) {

		RequestEntity<Void> request = RequestEntity.get(pensionDetailsURL + aadharNumber).headers(header).build();
		try {
			ResponseEntity<PensionerDetialsModel> response = restTemplate.exchange(request,
					PensionerDetialsModel.class);
			PensionerDetialsModel pensionDetails = response.getBody();
			return pensionDetails;
		} catch (Exception e) {
			log.error(aadharNumber + ": Data Not found");
		}

		return null;
	}

	/**
	 * This method will calulate the Pension Amount
	 * 
	 * @param classification
	 * @param salary
	 * @param allowance
	 * @return Double
	 */

	public Double calculatePension(String classification, Long salary, Long allowance) {
		if (classification.equalsIgnoreCase("Self")) {
			return (Double) ((salary * 0.8) + allowance);
		} else if (classification.equalsIgnoreCase("Family")) {
			return (Double) ((salary * 0.5) + allowance);
		}
		return null;

	}

	/**
	 * This Method will return the Commission fee according to Bank
	 * 
	 * @param bankType
	 * @return Integer
	 */

	public Integer calculateServiceCharge(String bankType) {
		if (bankType.equalsIgnoreCase("private")) {
			return 550;
		} else if (bankType.equalsIgnoreCase("public")) {
			return 500;
		}
		return 0;
	}

}
