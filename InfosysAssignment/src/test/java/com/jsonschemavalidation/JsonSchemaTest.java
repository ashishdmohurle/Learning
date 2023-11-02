package com.jsonschemavalidation;



import java.io.File;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonSchemaTest {
	
	@BeforeClass
	public void setup() {

		RestAssured.baseURI = "https://reqres.in/";
	}
	@Test
	public void schemaValidation(){
		
		File file = new File("Resources/JsonSchema.json");
		// Create a request specification
        RequestSpecification requestSpecification = RestAssured.given();

        // Set the query parameter
        requestSpecification.queryParam("page", 1);

        // Send the GET request
        Response response = requestSpecification.get("https://reqres.in/api/users");

        // Validate the response status code
        response.then().assertThat().statusCode(200);

        // Validate the response body against the JSON Schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));
	}

}
