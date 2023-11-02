package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPetReq;
import pojo.Category;
import pojo.Tag;

public class HelperFunctions {

	public RequestSpecification petStoreReq() {

		RestAssured.useRelaxedHTTPSValidation();

		RequestSpecification request = new RequestSpecBuilder().setBaseUri(APIEndpoints.BASE_URL)
				.setContentType(ContentType.JSON).build();
		return request;

	}

	public AddPetReq petAddToStore() {

		int actualId = 878976657;
		String actualName = "Ashish";

		AddPetReq reqBody = new AddPetReq();

		reqBody.setId(actualId);
		reqBody.setName(actualName);
		reqBody.setPhotoUrls(null);

		Category c = new Category();
		c.setId(actualId);
		c.setName(actualName);

		reqBody.setCategory(c);

		List<String> photoURL = new ArrayList<String>();

		photoURL.add("ABC");
		photoURL.add("XYZ");

		reqBody.setPhotoUrls(photoURL);

		List<Tag> tags = new ArrayList<>();

		Tag tag1 = new Tag();

		tag1.setId(0);
		tag1.setName("Micheal");

		Tag tag2 = new Tag();

		tag2.setId(1);
		tag2.setName("Jorden");

		tags.add(tag1);
		tags.add(tag2);

		reqBody.setTags(tags);
		reqBody.setStatus("available");

		System.out.println(reqBody);

		return reqBody;

	}

	public boolean verifyIdAndName(AddPetReq reqBody, Response response) {

		return reqBody.getId() == response.jsonPath().getInt("category.id")
				&& reqBody.getName().equals(response.jsonPath().get("category.name"));
	}

}
