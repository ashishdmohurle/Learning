package com.newCode;

import static org.testng.Assert.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestRegisterAPI {

	private static final String BASE_URL = "https://reqres.in/";

	@Test(dataProvider = "registrationData", dataProviderClass = TestData.class)
	public void registerAPITest(String username, String password) {

		JSONObject request = new JSONObject();

		request.put("email", username);
		request.put("password", password);
		
		
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().log().all().baseUri(BASE_URL).basePath("api/register")
				.header("Content-Type", "application/json").body(request).post();

		// Check the status code of the response
		int statusCode = response.statusCode();

		assertEquals(statusCode, 200);

		// Get the response body
		String responseBody = response.body().asString();

		assertTrue(responseBody.contains("id"));
		assertTrue(responseBody.contains("token"));

		JsonPath js = new JsonPath(responseBody);

		System.out.println("Id is :" + js.get("id"));
		System.out.println("Token is :" + js.get("token"));

	}
}
