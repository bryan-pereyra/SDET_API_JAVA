package com.unosquare;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class PostUsingJSON {

	@Test
	public static void postUsingJSON1() {
		JSONObject obj = new JSONObject();
		obj.put("name", "Bryan");
		obj.put("job", "SDET");

		try (FileWriter file = new FileWriter(
				"C:\\Users\\bryan.pereyra\\eclipse-workspace\\JavaAPI\\src\\test\\java\\com\\unosquare\\Json\\test1.json")) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (Reader reader = new FileReader(
				"C:\\Users\\bryan.pereyra\\eclipse-workspace\\JavaAPI\\src\\test\\java\\com\\unosquare\\Json\\test1.json")) {
		} catch (IOException e) {
			e.printStackTrace();
		}

		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(obj.toString());
		Response response = httpRequest.post("/users");
		Assert.assertEquals(response.getStatusCode(), 201);

		Reporter.log("This is the body I'm sending: " + obj);
		Reporter.log("This is the response code: " + response.getStatusCode());
		Reporter.log("This is the response body: " + response.asString());
	}
	
	@Test
	public static void postUsingJSON2() {
		JSONObject obj = new JSONObject();
		obj.put("name", "Mac");
		obj.put("job", "iOS Developer");

		try (FileWriter file = new FileWriter(
				"C:\\Users\\bryan.pereyra\\eclipse-workspace\\JavaAPI\\src\\test\\java\\com\\unosquare\\Json\\test2.json")) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (Reader reader = new FileReader(
				"C:\\Users\\bryan.pereyra\\eclipse-workspace\\JavaAPI\\src\\test\\java\\com\\unosquare\\Json\\test2.json")) {
		} catch (IOException e) {
			e.printStackTrace();
		}

		Response testResponse = given().header("Content-Type", "application/json").body(obj.toString())
				.when().post("https://reqres.in/api/users")
				.then().assertThat().statusCode(201).extract().response();

		Reporter.log("This is the body I'm sending: " + obj);
		Reporter.log("This is the response code: " + testResponse.getStatusCode());
		Reporter.log("This is the response body: " + testResponse.asString());
	}
}
