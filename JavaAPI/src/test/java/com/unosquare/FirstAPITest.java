package com.unosquare;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FirstAPITest {

	@Test
	public void test1() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/2");

		int statusCode = response.getStatusCode();

		// Validate all the items and status code
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Sucess 200 validation");

		response.then().body("data.id", equalTo(2));
		response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
		response.then().body("data.first_name", equalTo("Janet"));
		response.then().body("data.last_name", equalTo("Weaver"));
		response.then().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
		response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
		response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
		Reporter.log(response.body().asString());
	}

	@Test
	public void test2() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/unknown/2");

		int statusCode = response.getStatusCode();

		// Validate all the items and status code
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Sucess 200 validation");

		response.then().body("data.id", equalTo(2));
		response.then().body("data.name", equalTo("fuchsia rose"));
		response.then().body("data.year", equalTo(2001));
		response.then().body("data.color", equalTo("#C74375"));
		response.then().body("data.pantone_value", equalTo("17-2031"));
		response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
		response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
		Reporter.log(response.body().asString());
	}

	@BeforeMethod
	public void beforeMethod() {
	}
}
