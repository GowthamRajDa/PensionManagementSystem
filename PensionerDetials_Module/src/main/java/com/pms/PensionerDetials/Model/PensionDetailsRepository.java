package com.pms.PensionerDetials.Model;

import org.springframework.data.repository.CrudRepository;

/*
 * Interface for Hibernate Pension Details Model
 */


public interface PensionDetailsRepository extends CrudRepository<PensionerDetialsModel, String> {
	
	PensionerDetialsModel findByAadharNumber(String aadharNumber);

	PensionerDetialsModel findByAccountNumber(String accountNumber);
}
