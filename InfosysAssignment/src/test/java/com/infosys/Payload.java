package com.infosys;

public class Payload {
	
public  String registerUser() {
		
		String user = "{\r\n"
				+ "    \"email\": \"michael.lawson@reqres.in\",\r\n"
				+ "    \"password\": \"pistol\"\r\n"
				+ "}";
		
		return user;
	}


}
