package com.unosquare;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class FirstAPITest_Gherkin {

	@Test
	public void test1_Gherkin() {
		String test1Response = given().when().get("https://reqres.in/api/users/2")
		.then().assertThat().statusCode(200)
			   .assertThat().contentType(ContentType.JSON)
			   .assertThat().body("data.id", equalTo(2))
			   .assertThat().body("data.email", equalTo("janet.weaver@reqres.in"))
			   .assertThat().body("data.first_name", equalTo("Janet"))
			   .assertThat().body("data.last_name", equalTo("Weaver"))
			   .assertThat().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
			   .assertThat().body("support.url", equalTo("https://reqres.in/#support-heading"))
			   .assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
			   .extract().response().asString();
		
		Reporter.log(test1Response);
	}
	
	@Test
	public void test2_Gherkin() {
		String test2Response = given().when().get("https://reqres.in/api/unknown/2")
		.then().assertThat().statusCode(200)
			   .assertThat().contentType(ContentType.JSON)
			   .assertThat().body("data.id", equalTo(2))
			   .assertThat().body("data.name", equalTo("fuchsia rose"))
			   .assertThat().body("data.year", equalTo(2001))
			   .assertThat().body("data.color", equalTo("#C74375"))
			   .assertThat().body("data.pantone_value", equalTo("17-2031"))
			   .assertThat().body("support.url", equalTo("https://reqres.in/#support-heading"))
			   .assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
			   .extract().response().asString();
		
		Reporter.log(test2Response);
	}

	@BeforeMethod
	public void beforeMethod() {
	}
}
