Feature: Rest Assured Requests and APIs Tests

	Scenario Outline: User ws calls with true CEP and json requests to validate correct IBGE Tag value 
		Given I have set the base URL "https://viacep.com.br/ws"
		When I insert the <cep_resource>
		Then the result field IBGE should contain <response>
	Examples:
		|     cep_resource             | response   |	
		|	    "/01001000/json/"        | "3550308"  |


  Scenario Outline: User ws calls with inexistent CEP
		Given I have set the base URL "https://viacep.com.br/ws"
		When I insert the <cep_resource>
		Then the result field "erro" should contain <response>
	Examples:
		|     cep_resource             | response   |	
		|	    "/32235253/json/"        | "true"     |