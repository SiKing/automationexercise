package com.automationexercise.restassured;

import static io.restassured.RestAssured.given;

import java.net.URI;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.restassured.response.Response;

/**
 * I am using these tests to create / delete user for
 * {@link com.automationexercise.playwright}, so fields here need to be
 * {@code public}.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class RegisterTests {

    public static final String username = "Elias Ladopoulos";
    public static final String email = RandomStringUtils.insecure().nextAlphanumeric(10) + "@mailinator.com";
    public static final String password = RandomStringUtils.insecure().nextAlphanumeric(10);

    /**
     * API 11: POST To Create/Register User Account
     * 
     * API URL: https://automationexercise.com/api/createAccount
     * 
     * Request Method: POST
     * 
     * Request Parameters: name, email, password, title (for example: Mr, Mrs,
     * Miss), birth_date, birth_month, birth_year, firstname, lastname, company,
     * address1, address2, country, zipcode, state, city, mobile_number
     * 
     * Response Code: 201
     * 
     * Response Message: User created!
     */
    @Test
    public void test11_createUser() throws Exception {
	Response resp = given(). //
		multiPart("name", username). //
		multiPart("email", email). //
		multiPart("password", password). //
		multiPart("title", "Mr"). //
		multiPart("birth_date", "10"). //
		multiPart("birth_month", "10"). //
		multiPart("birth_year", "2000"). //
		multiPart("firstname", RandomStringUtils.insecure().nextAlphabetic(10)). //
		multiPart("lastname", RandomStringUtils.insecure().nextAlphabetic(10)). //
		multiPart("company", RandomStringUtils.insecure().nextAlphabetic(10)). //
		multiPart("address1", RandomStringUtils.insecure().nextAlphanumeric(10)). //
		multiPart("address2", RandomStringUtils.insecure().nextAlphanumeric(10)). //
		multiPart("country", "India"). //
		multiPart("zipcode", RandomStringUtils.insecure().nextAlphanumeric(10)). //
		multiPart("state", RandomStringUtils.insecure().nextAlphabetic(10)). //
		multiPart("city", RandomStringUtils.insecure().nextAlphabetic(10)). //
		multiPart("mobile_number", RandomStringUtils.insecure().nextNumeric(10)).

		when(). //
		post(new URI("https://automationexercise.com/api/createAccount")).

		then(). //
		log().all(). //
		statusCode(200). //
		extract().response();

	Assertions.assertEquals(201, resp.jsonPath().getInt("responseCode"));
	Assertions.assertEquals("User created!", resp.jsonPath().getString("message"));
    }

    /**
     * API 12: DELETE METHOD To Delete User Account
     * 
     * API URL: https://automationexercise.com/api/deleteAccount
     * 
     * Request Method: DELETE
     * 
     * Request Parameters: email, password
     * 
     * Response Code: 200
     * 
     * Response Message: Account deleted!
     */
    @Test
    public void test12_deleteUser() throws Exception {
	Response resp = given(). //
		multiPart("email", email). //
		multiPart("password", password). //

		when(). //
		delete(new URI("https://automationexercise.com/api/deleteAccount")).

		then(). //
		log().all(). //
		statusCode(200). //
		extract().response();

	Assertions.assertEquals(200, resp.jsonPath().getInt("responseCode"));
	Assertions.assertEquals("Account deleted!", resp.jsonPath().getString("message"));
    }
}
