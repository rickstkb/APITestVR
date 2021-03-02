package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_TC01 {

	Response response;
	RequestSpecification httprequest;
	
	@BeforeMethod
	void preSets ()
	{
		// URI
		RestAssured.baseURI = "https://viacep.com.br/ws/01001000/json/";
		
		// Objeto utilizado para requisição
		httprequest = RestAssured.given();
		
		// Objeto captado na resposta
		response = httprequest.request(Method.GET);
		
		// Imprime resposta no console 
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
	}
	
	
	@Test
	void chamaCEPValido()
	{
		String campoIBGE = response.getBody().jsonPath().getJsonObject("ibge");
		System.out.println(campoIBGE);
		Assert.assertEquals(campoIBGE, "3550308");
		
		// Validação de status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Validação de status line
		String statusline = response.getStatusLine();
		System.out.println(statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
}
