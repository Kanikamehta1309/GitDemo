Feature: Validating Place APIs 

@AddPlace 
Scenario Outline: Verify if place is successfully added using AddPlaceAPI 
	Given Add Place Request Payload with "<name>" "<language>" "<address>" 
	When user calls "AddPlaceAPI" Http "POST" request 
	Then the API call got success with status code 200 
	And verify the "status" in response code is "OK" 
	And verify the "scope" in response code is "APP" 
	And verify the "place_id" of "<name>" in response code using "GetPlaceAPI" Http "GET" request 
	
	Examples: 
		| name | language| address   |
		|kanika| English | Rohtak    |
		#	|devesh| spanish | bangalore |
		
		@DeletePlace 
		Scenario: Verify if delete functionality is working successfully 
			Given Delete place request payload 
			When user calls "DeletePlaceAPI" Http "POST" request 
			Then the API call got success with status code 200 
			And verify the "status" in response code is "OK" 
			
	