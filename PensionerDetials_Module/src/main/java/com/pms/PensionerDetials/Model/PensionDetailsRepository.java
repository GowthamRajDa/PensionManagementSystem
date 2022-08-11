package com.pms.PensionerDetials.Model;

import org.springframework.data.repository.CrudRepository;

public interface PensionDetailsRepository extends CrudRepository<PensionerDetialsModel, String> {
	PensionerDetialsModel findByAadharNumber(String aadharNumber);

	PensionerDetialsModel findByAccountNumber(String accountNumber);
}
