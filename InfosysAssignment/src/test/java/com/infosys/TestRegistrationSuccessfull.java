package com.infosys;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRegistrationSuccessfull {

	private static final String BASE_URL = "https://reqres.in/";
	
	Payload payload = new Payload();

	@Test
	public void testPostRegisterSuccessfulEndpoint() {

		// POST request response
		Response response = RestAssured.given().log().all()
				.baseUri(BASE_URL).basePath("api/register")
				.header("Content-Type", "application/json")
				.body(payload.registerUser())
				.post();

		// Get Content Type and Session ID
		String contentType = response.getContentType();
		String sessionID = response.getSessionId();
		System.out.println(sessionID);

		// Get Status Code and Status Line
		int statusCode = response.getStatusCode();
		String statusLine = response.getStatusLine();

		// Get Headers
		String headerValue = response.getHeader("Content-Type");
		System.out.println("The header value is : " + headerValue);

		// Extract Value using JsonPath using 1st method
		JsonPath resp = response.jsonPath();
		System.out.println("The token value is : " + resp.getString("token"));

		// Extract Value using JsonPath using 2nd method
		String jsonResponse = response.getBody().asString();
		JsonPath jsonPathResponse = new JsonPath(jsonResponse);
		String token = jsonPathResponse.getString("token");

		System.out.println("The token value is : " + token);
 
		// Additional assertions and verifications can be added here

		Assert.assertEquals(contentType, headerValue);
		Assert.assertNull(sessionID);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

}
