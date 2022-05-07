package com.healtheme.patientmycart.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.patientmycart.model.PatientOrderModel;

public class PatientMyCartValidationTest {
	
	PatientCartValidation val = new PatientCartValidation();
	
	@Test
	void toTestValidationForCartStreetaAddress()
	{
		PatientOrderModel po  = new PatientOrderModel("o04c5e85b", "harvikk@gmail.com", 13.3 , 5, 0.375, 2.875, "1333 South Park -%@", "Halifax", "Nova Scotia", "B3J 2K9" );
		assertEquals(" Invalid Street. ", val.checkValidation(po));
	}
	
	
	@Test
	void toTestValidationForCartCity()
	{
		PatientOrderModel po  = new PatientOrderModel("o04c5e85b", "harvikk@gmail.com", 13.3 , 5, 0.375, 2.875, "1333 South Park ", "Halifax12", "Nova Scotia", "B3J 2K9" );
		assertEquals(" Invalid City. ", val.checkValidation(po));
	}
	
	@Test
	void toTestValidationForCartProvince()
	{
		PatientOrderModel po  = new PatientOrderModel("o04c5e85b", "harvikk@gmail.com", 13.3 , 5, 0.375, 2.875, "1333 South Park ", "Halifax", "Nova Scotia123", "B3J 2K9" );
		assertEquals(" Invalid Province. ", val.checkValidation(po));
	}
	
	@Test
	void toTestValidationForCartPostalCode()
	{
		PatientOrderModel po  = new PatientOrderModel("o04c5e85b", "harvikk@gmail.com", 13.3 , 5, 0.375, 2.875, "1333 South Park ", "Halifax", "Nova Scotia", "338 2K9" );
		assertEquals(" Invalid Postal Code. ", val.checkValidation(po));
	}

	
}
