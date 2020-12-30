# todo: create some negative tests with invalid input such as null fell id

Feature: GET /fells/{id} - NEGATIVE TESTS WITH VALID INPUT

	#VerifyStatusCode
	Scenario: API provides 404 status code when ID does not exist
		When making a GET request to http://localhost:8080/api/v1/fells/NY000000
		Then the status code will be 404

	#VerifyResponsePayload
	Scenario: API provides json body validated against schema
		When making a GET request to http://localhost:8080/api/v1/fells/NY000000
		Then the body will conform to the schema in schemas/error_schema.json

	#VerifyResponsePayload
	Scenario: API provides error response body when fell not found
		When making a GET request to http://localhost:8080/api/v1/fells/NY000000
		Then the body will contain the status code 404
		And the body will contain the message Fell was not found for {os map ref=NY000000}
		And the body will contain the timestamp 2018-12-10 13:45:00

	#VerifyResponseHeaders
	Scenario: API provides content type header
		When making a GET request to http://localhost:8080/api/v1/fells/NY000000
		Then the content type will be application/json

	#VerifyApplicationState
	Scenario: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to http://localhost:8080/api/v1/fells/NY211104
		Then the headers will confirm only GET, HEAD AND OPTIONS methods are allowed

	#VerifyApplicationState
	Scenario Outline: API provides an error response when making an unsupported http method request
		When making an <unsupported http method> request to http://localhost:8080/api/v1/fells/NY000000
		Then the status code will be 405
		And the content type will be application/json
		And the body will conform to the schema in schemas/error_schema.json
		And the body will contain the status code 405
		And the body will contain the message <message>
		And the body will contain the timestamp 2018-12-10 13:45:00
		Examples:
			| unsupported http method | message                          |
			| POST                    | Method POST is not supported     |
			| DELETE                  | Method DELETE is not supported   |
			| PUT                     | Method PUT is not supported      |
			| PATCH                   | Method PATCH is not supported    |
