package com.pms.ProcessPensionInput.Models;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Gowtham
 * Pension Detail Model contain all the pension Details
 *
 */

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PensionerDetialsModel {

	private String aadharNumber;
	private String name;
	private String dob;
	private String pan;
	private Long salaryEarned;
	private Long allowances;
	private String classifcation;
	private String bankName;
	private String accountNumber;
	private String banktype;

}