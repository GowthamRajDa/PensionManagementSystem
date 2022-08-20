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
 * Processed pension model will have the Process pension details
 *
 */

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcessedPensionModel {
	private String aadharNumber;
	private Double pensionAmount;
	private Integer serviceCharge;

}
