package com.newpojoclass;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SuccessfullRegistration  {
	
	

    private static final String BASE_URL = "https://reqres.in/";

    @Test 
    public void testRegistrion() throws IOException {
    	
    	RequestTestData request = new RequestTestData();
    	
    	RestAssured.useRelaxedHTTPSValidation();
    	System.out.println("Ashish");
    	
    	
        Response response = RestAssured.given().with().body(request.testData()).log().all().baseUri(BASE_URL).basePath("/api/register")
                            .header("Content-Type", "application/json")
                            .post();

    }

}
