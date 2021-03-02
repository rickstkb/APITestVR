package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_TC02 {

	Response response;
	RequestSpecification httprequest;
	
	@BeforeMethod
	void preSets ()
	{
		// URI
		RestAssured.baseURI = "https://viacep.com.br/ws/32235253/json/";
		
		// Objeto utilizado para requisição
		httprequest = RestAssured.given();
		
		// Objeto captado na resposta
		response = httprequest.request(Method.GET);
		
		// Imprime resposta no console 
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
	}
	
	
	
	@Test
	void chamaCEPInvalido ()
	{		
//		Boolean campoErro = response.getBody().jsonPath().getJsonObject("erro");
		Boolean campoErro = response.jsonPath().get("erro");	
		System.out.println(campoErro);
		Assert.assertTrue(campoErro);
		
		// Validação de status code
		int statusCode2 = response.getStatusCode();
		System.out.println(statusCode2);
		Assert.assertEquals(statusCode2, 200);
		
		// Validação de status line
		String statusline2 = response.getStatusLine();
		System.out.println(statusline2);
		Assert.assertEquals(statusline2, "HTTP/1.1 200 OK");
	}	
}
