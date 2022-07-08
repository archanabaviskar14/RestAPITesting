package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;


public class placevalidationstep extends Utils {
	
	RequestSpecification rest;
	Response response;
	static String place_id;
	TestDataBuild data=new TestDataBuild();
	APIResources resourceAPI;

	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException 
	{
		
		rest=given()
				.spec(requestSpecification()).body(data.addPlacePayload(name,address,language));  //here we r calling requestspec() method 
																			//directly bcz we r inheritating Utils class
		  
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method)
	{
		APIResources resourceAPI=APIResources.valueOf(resource); //valueof() method will call the constructor
		System.out.println(resourceAPI.getResource());
		if(method.equalsIgnoreCase("POST"))
		{	
		response=rest.when()
				.post(resourceAPI.getResource())
				.then()
				.spec(responseSpecification()).extract().response();
		}
		else if(method.equalsIgnoreCase("GET"))
		{
			response=rest.when()
					.get(resourceAPI.getResource())
					.then()
					.spec(responseSpecification()).extract().response();
			
		}
		
	}

	@Then("the API got success with the success code {int}")
	public void the_api_got_success_with_the_success_code(Integer int1) 
	{
		
			assertEquals(response.getStatusCode(),200);		//static-import static org.junit.Assert.*;

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue)
	{
		String actualvalue=getJsonPath(response, keyvalue);
		assertEquals(actualvalue,expectedvalue);
		
	}
	

	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expected_name, String resource) throws IOException 
	{
		place_id=getJsonPath(response, "place_id");

		rest=given()
				.spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actual_name=getJsonPath(response, "name");
		assertEquals(actual_name,expected_name);		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException 
	{
		rest=given()
				.spec(requestSpecification()).body(data.DeletePlacePayload(place_id));
		
	 }




}
