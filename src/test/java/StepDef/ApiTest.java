package StepDef;

import static io.restassured.RestAssured.given;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class ApiTest {
	@Given("^User tries to execute get$")
	public void makesuregoogleIsup() {
	Response response = given()
			.when()
			.get("https://jsonplaceholder.typicode.com/users/1");
	System.out.println(response.body().asString());

	}
	
	@When("^User checks status code$")
	public void statusCode() {
	given()
			.when()
			.get("https://jsonplaceholder.typicode.com/users/1")
			.then()
			.statusCode(200);

	}
}
