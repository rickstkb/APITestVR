package StepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestAPICorreioSteps {

	String uriBase;
	Response response;
	RequestSpecification httprequest;
	
	@Given("I have set the base URL {string}")
	public void i_have_set_the_base_URL(String URI) {
		uriBase = URI;		
	}

	@When("I insert the {string}")
	public void i_insert_the(String resource) {
		
		// URI
		RestAssured.baseURI = uriBase + resource;
		
		// Objeto utilizado para requisição
		httprequest = RestAssured.given();
		
		// Objeto resposta captado após chamada do tipo get
		response = httprequest.request(Method.GET);
		
		// Imprime resposta json no console 
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
	}

	@Then("the result field IBGE should contain {string}")
	public void the_result_field_IBGE_should_contain(String valorIbge) {

		String campoIBGE = response.getBody().jsonPath().getJsonObject("ibge");
		System.out.println(campoIBGE);
		Assert.assertEquals(campoIBGE, valorIbge);
		
		// Validação de status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Validação de status line
		String statusline = response.getStatusLine();
		System.out.println(statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		System.out.println("\n\n");
	}

	@Then("the result field {string} should contain {string}")
	public void the_result_field_should_contain(String chaveErro, String valorErro) {

		Boolean campoErro = response.jsonPath().get("erro");	
		System.out.println(campoErro);
		Boolean valorErroBdd = Boolean.parseBoolean(valorErro);
		Assert.assertEquals(campoErro, valorErroBdd);
		
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
