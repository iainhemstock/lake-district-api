Feature: GET /fells - BASIC POSITIVE TESTS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And each page of results is configured to display a single result

	Scenario: API provides OK status code on successful request
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the status code will be 200

	Scenario: API provides json body validated against schema
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the body will conform to the schema in schemas/collection_resource_schema.json

	Scenario: API provides a simplified view of a fell
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the body will contain a simplified fell with name Great Gable
		And region Central Lake District
		And url http://localhost:8080/api/v1/fells/NY211104

	Scenario: API provides content type header
		When making a GET request to http://localhost:8080/api/v1/fells
		Then the content type will be application/json

	Scenario: API confirms that this endpoint only accepts idempotent requests
		When making an OPTIONS request to http://localhost:8080/api/v1/fells
		Then the headers will confirm only GET, HEAD AND OPTIONS methods are allowed