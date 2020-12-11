Feature: GET /fells?page={}&size={} - NEGATIVE TESTING WITH INVALID PARAMETERS

	Background:
		Given Great Gable, Helvellyn and Scafell Pike exist

	Scenario: API provides Bad Request status code when offset is negative
		When making a GET request to http://localhost:8080/api/v1/fells?offset=-1&limit=1
		Then the status code will be 400
