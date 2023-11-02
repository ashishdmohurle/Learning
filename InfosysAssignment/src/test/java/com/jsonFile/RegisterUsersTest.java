package com.jsonFile;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RegisterUsersTest {

	@BeforeClass
	public void setup() {

		RestAssured.baseURI = "https://reqres.in/";
	}

	@Test
	public void registerUser() throws IOException {
		// Read the JSON file
		FileInputStream jsonFile = new FileInputStream("Resources/JsonFile.json");
		// Create a RestAssured request
		Response response = RestAssured.given()
							.basePath("api/register")
							.header("Content-Type", "application/json")
							.body(jsonFile)
							.post();

		int actualStatusCode = response.getStatusCode();

		Assert.assertEquals(200, actualStatusCode);
	}

}
