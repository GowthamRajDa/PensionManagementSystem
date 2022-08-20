package com.pms.PensionerDetials.DAO;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pms.PensionerDetials.Model.PensionDetailsRepository;
import com.pms.PensionerDetials.Model.PensionerDetialsModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Gowtham
 * 
 * This class contains the Data Access Object for Pension Details Database.
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Component
public class PensionDetailsLoader implements CommandLineRunner {

	@Autowired
	private PensionDetailsRepository pensionerDetailsRepo;
	private List<PensionerDetialsModel> pensionDetailsList = new ArrayList<PensionerDetialsModel>();

	@Override
	public void run(String... args) throws Exception {

//		File PensionDetailsCSV = new File("src/main/resources/PensionDetails.csv");
		InputStream inputStream = getClass().getResourceAsStream("/PensionDetails.csv");
		Scanner scnObj = new Scanner(inputStream);
		scnObj.nextLine(); // for header read

		while (scnObj.hasNextLine()) {

			String[] PensionerDetails = scnObj.nextLine().split(",");

			String aadharNumber = PensionerDetails[0];
			String Name = PensionerDetails[1];
			String dob = PensionerDetails[2];
			String PAN = PensionerDetails[3];
			Long SalaryEarned = Long.parseLong(PensionerDetails[4]);
			Long Allowances = Long.parseLong(PensionerDetails[5]);
			String classifcation = PensionerDetails[6];
			String BankName = PensionerDetails[7];
			String AccountNumber = PensionerDetails[8];
			String Banktype = PensionerDetails[9];

			PensionerDetialsModel PensionDetail = new PensionerDetialsModel(aadharNumber, Name, dob, PAN, SalaryEarned,
					Allowances, classifcation, BankName, AccountNumber, Banktype);

//			System.out.println(PensionDetail);
			pensionDetailsList.add(PensionDetail);
			
//			System.out.println(aadharNumber + " Loaded");
		}
		
		scnObj.close();
		
		pensionerDetailsRepo.saveAll(pensionDetailsList);

	}
	
	/**
	 * It will provide full Aadhar details by AadharNumber
	 * @param aadhar
	 * @return PensionerDetialsModel
	 */
	
	public PensionerDetialsModel getPensionerByAadhar(String aadhar) {

		PensionerDetialsModel pensionDetials;
		if (pensionerDetailsRepo.existsById(aadhar)) {
			pensionDetials = pensionerDetailsRepo.findByAadharNumber(aadhar);
		} else {

			return null;
		}

		return pensionDetials;
	}
}
