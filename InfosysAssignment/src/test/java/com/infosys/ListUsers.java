package com.infosys;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ListUsers {

	@Test
	
	public void methodOne() {
		
		
		// Send a GET request and store response
		String BASE_URL = "https://reqres.in";
		RestAssured.useRelaxedHTTPSValidation();
		Response response = given().baseUri(BASE_URL).basePath("api/users").queryParam("page", 2).get();

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

		// JsonPath class to get node value
		JsonPath resp = response.jsonPath();
		System.out.println("FirstName for index at is : " + resp.get("data[4].first_name").toString());

		// validations using different Assertions
		Assert.assertEquals(contentType, headerValue);
		Assert.assertNull(sessionID);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void methodSecond() {

		RestAssured.baseURI = "https://reqres.in";
		String response = given().queryParam("page", "2").when().get("/api/users").then().log().all().assertThat()
				.statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK")
				.header("Server", "cloudflare").body("page", equalTo(2)).extract().response().asString();

		JsonPath js = new JsonPath(response);
		int actualPerPageRecord = js.getInt("per_page");

		JsonPath js2 = new JsonPath(response);
		int expecetedPerPageRecord = js2.getInt("data.size()");

		Assert.assertEquals(actualPerPageRecord, expecetedPerPageRecord);

	}

}
