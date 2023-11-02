package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIEndpoints;
import resources.HelperFunctions;

public class stepdefinitions extends HelperFunctions {

	RequestSpecification postReq;
	RequestSpecification putReq;
	RequestSpecification getReq;
	Response postResponse;
	Response putResponse;
	Response getResponse;
	static int petId;

	@Given("create pet endpoint")
	public void create_pet_endpoint() {

		postReq = RestAssured.given().log().all()
					.spec(petStoreReq().request())
					.body(petAddToStore())
					.basePath(APIEndpoints.POST_PET);
	}

	@When("I send a POST request to endpoint")
	public void i_send_a_post_request_to_endpoint() {

		postResponse = postReq.when().post();
		System.out.println("POST RESPONSE :- " + postResponse.asPrettyString());

		String response = postResponse.asString();

		JsonPath js = new JsonPath(response);
		int petId = js.getInt("category.id");
		this.petId = petId;

	}

	@Then("the response statusCode should be {int}")
	public void the_response_status_code_should_be(Integer int1) {

		if (postResponse != null) {
			int postStatusCode = postResponse.getStatusCode();
			Assert.assertEquals(postStatusCode, 200);
			System.out.println("POST STATUSCODE: " + postStatusCode);

		} else if (putResponse != null) {
			int putStatusCode = putResponse.getStatusCode();
			Assert.assertEquals(putStatusCode, 200);
			System.out.println("PUT STATUSCODE: " + putStatusCode);

		} else if (getResponse != null) {
			int getStatusCode = getResponse.getStatusCode();
			Assert.assertEquals(getStatusCode, 200);
			System.out.println("GET STATUSCODE: " + getStatusCode);
		}
	}

	@Then("the pet should be created successfully with passed details")
	public void the_pet_should_be_created_successfully_with_passed_details() {

		Assert.assertTrue(verifyIdAndName(petAddToStore(), postResponse));

	}

	@Given("update pet details endpoint")
	public void update_pet_details_endpoint() {

		putReq = RestAssured.given().log().all()
					.spec(petStoreReq().request())
					.body(petAddToStore())
					.basePath(APIEndpoints.PUT_PET);
	}

	@When("I send a PUT request to endpoint")
	public void i_send_a_put_request_to_endpoint() {

		putResponse = putReq.when().put();
		System.out.println("PUT RESPONSE :-  " + putResponse.asPrettyString());

	}

	@Then("the pet details should get updated successfully with passed details")
	public void the_pet_details_should_get_updated_successfully_with_passed_details() {

		Assert.assertTrue(verifyIdAndName(petAddToStore(), putResponse));
	}

	@Given("fetching the pet details endpoint")
	public void fetching_the_pet_details_endpoint() {

		getReq = RestAssured.given().log().all()
					.spec(petStoreReq().request())
					.basePath(APIEndpoints.GET_PET_BY_ID + petId);

	}

	@When("I get the pet details by id")
	public void i_get_the_pet_details_by_id() {

		getResponse = getReq.when().get();
		System.out.println("GET RESPONSE :-  " + getResponse.asPrettyString());

	}

	@Then("the pet details should be retrieved successfully")
	public void the_pet_details_should_be_retrieved_successfully() {

		Assert.assertTrue(verifyIdAndName(petAddToStore(), getResponse));

	}

}
