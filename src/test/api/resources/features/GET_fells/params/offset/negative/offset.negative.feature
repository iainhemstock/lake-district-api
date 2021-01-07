Feature: GET /fells?offset={invalid} - NEGATIVE TESTING WITH INVALID PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default page size is 1

	Scenario: /fells?offset=-1 - API returns BAD REQUEST status code when invalid offset is specified
		When making a GET request to /fells?offset=-1
		Then the status code will be 400

	Scenario: /fells?offset=-1 - API provides json body validated against schema when invalid offset is specified
		When making a GET request to /fells?offset=-1
		Then the body will conform to the schema in schemas/error_schema.json

	Scenario: /fells?offset=-1 - API returns error response when invalid offset is specified
		When making a GET request to /fells?offset=-1
		Then the body will contain the status code 400 BAD_REQUEST
		And the body will contain the message Offset cannot be negative
		And the body will contain the timestamp 2018-12-10 13:45:00


