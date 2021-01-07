Feature: /fells?limit=[invalid] - NEGATIVE TESTS WITH INVALID INPUT

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist
		And the default offset of items returned is 0

	Scenario: /fells?limit=0 - API returns BAD_REQUEST status code when page size is 0
		When making a GET request to /fells?limit=0
		Then the status code will be 400

	Scenario: /fells?limit=0 - API provides json body validated against schema when page size is 0
		When making a GET request to /fells?limit=0
		Then the body will conform to the schema in schemas/error_schema.json