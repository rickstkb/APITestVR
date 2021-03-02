package com.qa.rest.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCallBDD 
{

	@Test
	public void test1correio ()
	{
//		given().
//		when().
//		then()
//		assert()
		
		given().
		when().
			get("https://viacep.com.br/ws/01001000/json/").
		then().
			assertThat().
			statusCode(200).
			and().
			contentType("application/json");
//			and().
//			body("ibge", hasValue("3550308"));
	}
	
	
}
