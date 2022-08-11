package com.pms.PensionerDetials.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PensionerDetialsModel {

	@Id
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
