package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class AddPlace extends Utils {

	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	Utils util = new Utils();
	APIResources resourceAPI;
	static String place_id;

	@Given("^Add Place Request Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Request_Payload_with(String name, String language, String address) throws IOException {
		request = given().spec(requestSpecification()).body(data.AddPlacePayload(name, language, address));
	}

	@When("user calls {string} Http {string} request")
	public void user_calls_Http_request(String APIres, String method) {
		resourceAPI = APIResources.valueOf(APIres);

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST")) {
			System.out.println("resourceAPI.getResource(): " + resourceAPI.getResource());
			response = request.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = request.when().get(resourceAPI.getResource());
		}

	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(int arg1) {
		assertEquals(response.getStatusCode(), arg1);
	}

	@Then("verify the {string} in response code is {string}")
	public void verify_the_in_response_code_is(String key, String value) {
		assertEquals(getJsonPath(response, key), value);
	}

	@Then("verify the {string} of {string} in response code using {string} Http {string} request")
	public void verify_the_of_in_response_code_using(String placeId, String expectedname, String APIres, String method)
			throws IOException {
		place_id = getJsonPath(response, placeId);
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_Http_request(APIres, method);
		verify_the_in_response_code_is("name", expectedname);

	}

	@Given("Delete place request payload")
	public void delete_place_request_payload_with() throws IOException {

		request = given().spec(requestSpecification()).body(data.DeletePlacePayload(place_id));
	}

}
