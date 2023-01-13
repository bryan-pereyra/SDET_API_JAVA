package com.unosquare;

import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FirstPost {

	@Test
	public static void firstPost() {
		HashMap<Object,Object> requestParams = new HashMap<Object,Object>();

		requestParams.put("name", "JohnAPI");
		requestParams.put("job", "QA");

		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(requestParams.toString());
		Response response = httpRequest.post("/users");
		
		Reporter.log(response.asString());
	}
}
