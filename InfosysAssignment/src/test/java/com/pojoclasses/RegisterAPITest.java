package com.pojoclasses;

import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class RegisterAPITest {

	// Define the base URL of the API
	private static final String BASE_URL = "https://reqres.in/";
	
	

	// Define a TestNG test method that accept test data
	@Test(dataProvider = "registrationData", dataProviderClass = TestDataProviders.class)
	public void testPostRegisterSuccessfulEndpoint(RegistrationRequest requestData) {
		RestAssured.useRelaxedHTTPSValidation();
		// Send the POST request with the requestData object
		Response response = RestAssured.given().log().all()
				.baseUri(BASE_URL).basePath("api/register")
				.header("Content-Type", "application/json")
				.body(requestData)
				.post();

		// Check the response status code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		// Deserialize the response to the RegistrationResponse POJO class
		RegistrationResponse pojoResponse = response.as(RegistrationResponse.class);

		// Perform assertion on the response object
		String token = pojoResponse.getToken();
		Assert.assertNotNull(token);
	}
}
