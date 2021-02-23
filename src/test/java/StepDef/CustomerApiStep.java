package StepDef;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pojoClasses.CustomerPojo;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class CustomerApiStep {
	CustomerPojo customer = new CustomerPojo();
	private RequestSpecification request;
	private Response response;
	
	
	@Given("^I have the data to create customer$")
	public void i_have_the_data_to_create_customer(DataTable dt) throws Throwable {
		List<List<String>> list = dt.asLists(String.class);
		ArrayList<String> addresses = new ArrayList<String>();
		addresses.add(list.get(1).get(3));
		addresses.add(list.get(1).get(4));
		
		customer.setFirstName(list.get(1).get(1));
		customer.setLastName(list.get(1).get(2));
		customer.setAddresses(addresses);
	}

	@Given("^I use customer header$")
	public void i_use_customer_header() throws Throwable {
		request = given().header("Content-Type","application/json").header("Accept-Charset","UFT-8");
		
	}

	@When("^I create post request to create customer$")
	public void i_create_post_request_to_create_customer() throws Throwable {
		response = request
				.when()
				.body(customer)
				.log()
				.body()
				.get("https://jsonplaceholder.typicode.com/users/1");
	}

	@Then("^I get status code (\\d+) from database$")
	public void i_get_status_code_from_database(int arg1) throws Throwable {
	  
	}

	@Then("^response body should contain$")
	public void response_body_should_contain(DataTable arg1) throws Throwable {
	   
	}


}
